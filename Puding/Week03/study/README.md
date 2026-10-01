# 3주차 Spring Boot 실습 환경

3주차 API 워크북의 Spring 선택지를 위한 시작 프로젝트입니다. Java 21, Spring Boot 3.5.0, Gradle, Spring Web, JDBC, MySQL Driver, Lombok을 사용합니다. 3주차에는 ORM이나 DTO를 추가하지 않고 `JdbcTemplate`으로 Raw SQL을 작성합니다.

## 실행 준비

2주차의 [스키마](../../Week02/01_schema.sql)와 [초기 데이터](../../Week02/02_seed.sql)를 복사한 `umc_week03_library` MySQL 데이터베이스를 사용합니다. 3주차 POST 요청이 2주차 검증 데이터를 바꾸지 않도록 분리했습니다. 이 컴퓨터의 MySQL 컨테이너는 `127.0.0.1:13306`에 연결되어 있어 워크북 예시의 `3306`과 포트가 다릅니다. 기존 DB에서는 스키마와 초기 데이터를 다시 실행하지 마세요.

이 컴퓨터에는 2주차 MySQL 비밀번호를 재사용하고 3주차 DB를 가리키는 `.env`가 준비되어 있습니다. 다른 컴퓨터에서는 먼저 [2주차 안내](../../Week02/README.md)에 따라 MySQL 컨테이너를 준비한 뒤 `python3 setup_db.py`를 실행하세요. 이 스크립트는 2주차 SQL의 `USE` 대상만 3주차 DB로 바꿔 적재하며, DB가 이미 있으면 아무것도 변경하지 않습니다. 이어서 `cp -n .env.example .env`로 새 파일을 만들고 `DB_PW`를 본인의 MySQL 비밀번호로 바꿉니다. `.env`는 Git에서 제외됩니다. Java 21이 없다면 macOS에서는 `brew install openjdk@21`로 설치합니다.

새 터미널에서 실행할 때는 다음처럼 환경변수를 불러옵니다.

```sh
cd /Users/moongawon/MY_Work/Puding/Week03/study
export JAVA_HOME="$(brew --prefix openjdk@21)/libexec/openjdk.jdk/Contents/Home"
set -a
source .env
set +a
./gradlew test
./gradlew bootRun
```

서버가 시작되면 `http://localhost:8080`에서 대기합니다. 기본 경로 `/`의 404 응답은 정상이며, 도서 목록은 `/books`에서 확인합니다.

## 구현한 API

- `GET /books`: 전체 도서를 ID 순서로 JSON 반환
- `POST /books`: `categoryId`, `title`, `description`을 받아 도서 등록, 201 반환
- `GET /books/category/{categoryId}`: 해당 분류의 도서만 JSON 반환
- `POST /rentals`: `userId`, `bookId`를 받아 현재 시각에 대여 등록, 7일 뒤를 반납 예정 시각으로 저장, 201 반환

코드는 `com.umc.study` 아래에 Controller, Service, Repository 순서로 나뉩니다. DB 조회와 저장에는 `JdbcTemplate`과 `?` 파라미터 바인딩을 사용합니다. 3주차 범위에 맞춰 DTO와 ORM은 사용하지 않았습니다.

```sh
curl -i http://localhost:8080/books
curl -i http://localhost:8080/books/category/1
curl -i -X POST http://localhost:8080/books \
  -H 'Content-Type: application/json' \
  -d '{"categoryId":1,"title":"클린 코드","description":"애자일 소프트웨어 장인 정신"}'
curl -i -X POST http://localhost:8080/rentals \
  -H 'Content-Type: application/json' \
  -d '{"userId":1,"bookId":1}'
```

위 POST 요청은 3주차 DB에 실제 행을 추가합니다. 선택 미션인 `PATCH /rentals/{rentalId}/return`은 별도 단계입니다.

요구사항 분해와 실행 검증 내용은 [미션 기록](docs/mission-record.md)에 정리했습니다.
