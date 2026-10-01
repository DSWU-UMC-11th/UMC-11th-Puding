"""Create a Week03 database from the Week02 workbook schema and seed once."""

from pathlib import Path
import subprocess


DATABASE = "umc_week03_library"
WEEK02 = Path(__file__).resolve().parents[2] / "Week02"
MYSQL = WEEK02 / "mysql.sh"


def mysql(sql: str) -> str:
    result = subprocess.run(
        [str(MYSQL), "--batch", "--skip-column-names"],
        input=sql,
        text=True,
        capture_output=True,
        check=True,
    )
    return result.stdout


def sql_for_week03(filename: str) -> str:
    source = (WEEK02 / filename).read_text()
    old_use = "USE umc_week02_library;"
    if source.count(old_use) != 1:
        raise ValueError(f"Expected exactly one Week02 USE statement in {filename}")
    return source.replace(old_use, f"USE `{DATABASE}`;")


def main() -> None:
    exists = mysql(
        "SELECT COUNT(*) FROM INFORMATION_SCHEMA.SCHEMATA "
        f"WHERE SCHEMA_NAME = '{DATABASE}';"
    ).strip()
    if exists != "0":
        print(f"{DATABASE} already exists; no schema or seed was changed")
        return

    schema = sql_for_week03("01_schema.sql")
    seed = sql_for_week03("02_seed.sql")
    mysql(
        f"CREATE DATABASE `{DATABASE}` "
        "CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;"
    )
    try:
        mysql(schema)
        mysql(seed)
    except Exception:
        mysql(f"DROP DATABASE IF EXISTS `{DATABASE}`;")
        raise
    print(f"Created {DATABASE} from Week02 schema and seed")


if __name__ == "__main__":
    main()
