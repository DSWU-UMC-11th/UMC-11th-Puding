# UMCine — 3주차 TanStack Router · Tailwind CSS

2주차 영화 목록을 별도 프로젝트로 복사해 파일 기반 라우팅과 Tailwind CSS v4로 확장한 결과물입니다. 영화 데이터와 이미지·아이콘은 2주차 제공 자료를 그대로 사용합니다.

## 실행

Node.js 22와 pnpm 11 기준입니다.

```sh
cd Puding/Week03/react
pnpm install --frozen-lockfile
pnpm dev
```

`pnpm typecheck`와 `pnpm build`로 타입 검사 및 프로덕션 빌드를 확인할 수 있습니다. `src/routeTree.gen.ts`는 TanStack Router 플러그인이 생성하므로 직접 수정하지 않습니다.

## 화면과 사용

- `/`: 영화 10편 목록, 포스터·제목에서 상세 이동, 북마크 토글, 페이지 선택
- `/search`: 검색어 입력 안내
- `/search?query=스파이더맨`: 제목·원제에서 검색해 검색어, 결과 수, 포스터, 제목, 원제, 개봉일, 줄거리를 표시합니다. 결과가 없으면 별도 안내를 표시합니다.
- `/movies/1`: 배경 이미지·포스터와 영화 상세 정보. 존재하지 않는 ID는 `영화를 찾을 수 없어요.`로 안내합니다.

검색어는 URL의 `query`에 저장됩니다. 뒤로 가기, 앞으로 가기, URL 직접 입력 및 새로고침 시 같은 검색 결과를 볼 수 있습니다. 영화 카드와 검색 결과는 `Link`로 상세 라우트에 이동합니다. 헤더는 현재 영화/검색 라우트만 활성화합니다.

목록의 북마크와 페이지 선택은 2주차와 같이 화면 내부 상태입니다. 페이지 번호는 실제 서버 페이지를 요청하지 않고 활성 번호만 표시합니다. 로그인·내 정보는 디자인에 포함된 정적 표시입니다.

## 구조

- `src/routes`: `/`, `/search`, `/movies/$movieId`와 공통 레이아웃
- `src/pages/movies`: 목록·검색·상세 화면
- `src/components/layout`, `src/components/movies`: 헤더·푸터, 카드·그리드·페이지 선택
- `src/utils/cn.ts`: `clsx`와 `tailwind-merge`를 이용한 조건부 Tailwind class 조합
- `src/index.css`: Tailwind import와 기본 글꼴·최소 너비만 유지

## 디자인 기준과 한계

2주차 Figma 확인을 바탕으로 만든 영화 목록의 수치(흰 헤더, `#f6f7f9` 배경, 최대 1280px 내용 폭, 데스크톱 5열, 포스터 비율·간격, 북마크 색, TMDB 푸터)를 유지했습니다. 목록은 화면 폭 1200/768/480px을 경계로 5/3/2/1열을 사용합니다. 이번 주차 Figma의 공개 프레임 제목은 확인했으나 상세 프레임의 정확한 치수와 모든 요소는 확대 시 로그인 안내로 확인할 수 없었습니다. 상세 화면은 제공된 배경·포스터·영화 정보로 구성했습니다.

[미션 기록](docs/mission-record.md)에 핵심 코드 위치와 검증 결과를 정리했습니다.
