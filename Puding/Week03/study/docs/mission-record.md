# 3주차 Spring API 미션 기록

## 지난 주차와 연결한 내용

2주차의 `book`, `category`, `users`, `rental` 테이블과 초기 데이터를 그대로 사용했다. 3주차 API의 쓰기 요청이 2주차 SQL 검증 결과를 바꾸지 않도록 같은 MySQL 컨테이너에 `umc_week03_library`를 따로 만들었다. `setup_db.py`는 2주차 SQL의 DB 선택 구문만 3주차 이름으로 바꿔 최초 한 번 적재한다.

## 요구사항을 나눈 과정

1. 기본 실습: `GET /books`로 전체 도서 목록을 조회하고, `POST /books`로 분류 ID·제목·설명을 받아 도서를 등록한다. 새 도서의 `is_available`은 `true`로 저장한다.
2. 필수 미션: `GET /books/category/{categoryId}`로 해당 분류의 도서만 조회한다. `POST /rentals`는 회원 ID·도서 ID를 받아 대여 시각과 7일 뒤 반납 예정 시각을 저장한다.
3. 워크북의 3주차 방식에 맞춰 Controller → Service → Repository로 구성하고, Repository에서 `JdbcTemplate`과 `?` 파라미터를 사용했다. 요청은 `Map<String, Object>`, 목록 응답은 `List<Map<String, Object>>`로 처리했다. DTO와 ORM은 4주차 주제이므로 사용하지 않았다.

## 실행 및 확인 기록

- 2026-10-01, Java 21에서 `./gradlew test --no-daemon` 통과. 테스트가 실제 3주차 DB에 연결해 `book` 행 수를 조회했다.
- 별도 임시 DB에 2주차 스키마·초기 데이터를 적재하고 네 엔드포인트를 HTTP로 확인했다. 전체 목록은 초기 도서 3권, 분류 1은 2권, 없는 분류는 빈 목록이었다.
- 임시 DB에서 도서 등록과 대여 등록은 각각 HTTP 201을 반환했다. 등록한 도서가 목록에 보였고, 저장된 대여의 `due_at - rented_at`은 604800초(7일)였다. 검증 후 임시 DB는 삭제했다.
- API 실습 전 2주차 DB와 3주차 DB의 `book`·`rental` 행 수는 각각 3권·2건이었다. Postman 쓰기 요청은 3주차 DB에만 반영됐고, 2주차 DB는 3권·2건으로 유지됐다.

## 실제 Postman 진행 과정

- `GET /books`는 전체 도서 목록과 HTTP 200, `GET /books/category/1`은 분류 1의 도서 2권과 HTTP 200을 확인했다.
- 처음 `POST /books`를 요청 본문 없이 보내 HTTP 400을 받았다. Postman의 **Body → raw → JSON**에서 `categoryId`, `title`, `description`을 입력해 다시 보내자 HTTP 201과 도서 등록 완료 문구를 확인했다.
- `POST /rentals`에는 JSON으로 `userId`와 `bookId`를 보내 HTTP 201과 도서 대여 완료 문구를 확인했다.
- 사용자가 위 네 요청의 Postman 화면을 직접 캡처했다. 캡처 파일은 현재 이 저장소에서 확인되지 않아 제출 페이지에 별도로 첨부해야 한다.

## 제출 전 확인할 항목

- 캡처 4장에 각 API의 URL, POST 요청 본문, 상태 코드, 응답이 보이는지 확인하고 제출 페이지에 첨부한다.
- 위 400 오류 해결 과정을 참고해 본인이 이해한 내용과 아직 헷갈리는 점을 회고로 작성한다.
- 선택 미션인 반납 API는 현재 범위에 포함하지 않았다.
