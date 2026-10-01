# 3주차 TanStack Router · Tailwind CSS 미션 기록

- [원문 워크북](https://app.notion.com/p/f5e5056fdfa9824ea12f01fd52c35988)
- [Figma 영화 페이지](https://www.figma.com/design/erFuXxEy5svB8Be5gjIqdu/?node-id=0-1)

## 필수 미션

TanStack Router Vite 플러그인으로 `src/routes`의 파일 기반 라우트를 연결했습니다. 공통 헤더·푸터는 `src/routes/__root.tsx`에 두고, 목록·검색·상세를 각각 `/`, `/search?query=...`, `/movies/$movieId`에 연결했습니다. `src/main.tsx`는 생성된 `routeTree.gen.ts`로 `RouterProvider`를 만듭니다.

검색은 `src/routes/search.tsx`에서 `query`를 검증합니다. `src/pages/movies/search-page.tsx`는 `trim().toLowerCase()`한 검색어로 제목과 원제를 비교합니다. 폼 제출 시 `useNavigate`로 URL을 바꾸고, URL이 바뀌면 입력값을 동기화합니다. 검색어 없음·결과 없음 상태를 각각 표시합니다. 검색 결과에는 포스터, 제목, 원제, 개봉일과 줄거리가 있고 `Link`로 상세 화면을 엽니다.

상세는 `useParams({ from: "/movies/$movieId" })`로 ID를 읽고 로컬 데이터에서 영화를 찾습니다. `/movies/1`에 배경과 포스터가 함께 보이며, 없는 ID는 안내 메시지를 표시합니다. 목록 카드의 포스터·제목도 `Link`로 상세로 이동합니다.

Tailwind CSS v4와 Vite 플러그인을 연결하고 화면 스타일을 TSX의 utility class로 옮겼습니다. `src/index.css`에는 Tailwind import, 글꼴, 최소 너비만 있습니다. 북마크·페이지 버튼·활성 헤더의 상태별 class는 `src/utils/cn.ts`의 `cn`으로 조합합니다.

## 선택 미션

헤더에서 현재 경로를 확인해 영화와 검색 중 해당 메뉴에만 활성 스타일 및 `aria-current="page"`를 표시합니다. 영화 상세는 영화 메뉴에 포함됩니다. 카드 그리드는 1200/768/480px에서 5/3/2/1열로 바뀝니다.

## 최종 확인

- `pnpm build`: TypeScript 검사 및 Vite 프로덕션 빌드 통과.
- 브라우저에서 목록 → `/movies/1` 이동, 배경 이미지와 영화 제목 확인.
- `/search`의 검색어 없음 안내와 `스파이더맨` 검색 결과 2편 확인. 검색 URL 새로고침 후 검색어와 결과 유지.
- `/movies/999`의 영화 없음 안내 확인.
- 390/700/1000/1440px에서 각각 1/2/3/5열, 가로 넘침 없음.
- 브라우저 Console 오류·경고 없음.
