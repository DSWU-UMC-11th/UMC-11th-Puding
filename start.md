# UMC 11th web 스터디

Front / Backend 파트가 주차별 미션을 올리는 레포입니다.
PR을 올리기 전에 아래 컨벤션을 한 번 확인해 주세요.

기준: [Notion Git-Hub 가이드](https://app.notion.com/p/Git-Hub-3dc5056fdfa9806494d0c96ba2e136cc)

## 폴더 구조

```
MY_Work/
└── Puding/                  # 로컬 Git 저장소 루트
    ├── start.md
    └── Puding/              # 저장소 안의 닉네임 폴더
        ├── week00/
        │   └── 미션 파일
        ├── week01/
        │   └── 미션 파일
        └── week10/
            └── 미션 파일
```

- 주차는 두 자리로 씁니다. (`week00` ~ `week10`)
- 닉네임은 **`Puding`**이며, 폴더와 브랜치 모두 대문자 `P`를 포함한 같은 이름을 씁니다.
- **내 폴더 안의 파일만 수정합니다.** 다른 사람 폴더나 레포 공통 파일은 건드리지 않습니다.
- 이전 주차 코드를 이어서 쓸 때는 새 주차 폴더로 복사하는 커밋을 따로 만듭니다.
  - 예: `CHORE: 0주차 Front 프로젝트를 1주차로 복사`

## 저장소와 브랜치

- 조직 원본 저장소: `DSWU-UMC-11th/UMC-11th-Puding`
- 조직 원본을 개인 GitHub 계정으로 **fork**한 뒤, 개인 fork에서 작업합니다.
- 로컬 원격 이름은 **`origin` = 개인 fork**, **`upstream` = 조직 원본**으로 구분합니다.
- 작업 브랜치는 **`Puding`**입니다. 개인 fork의 `Puding` 브랜치에만 push합니다.
- **조직 원본 저장소와 `main` 브랜치에 직접 push하지 않습니다.**

### 최초 설정

아래 흐름은 조직 원본에 README와 `main`이 이미 있고, 개인 fork를 새로 clone한 경우의 예시입니다. 명령은 clone한 저장소 루트에서 실행합니다.

```bash
git remote add upstream https://github.com/DSWU-UMC-11th/UMC-11th-Puding.git
git switch -c Puding
```

이미 로컬 저장소가 있다면 먼저 `git remote -v`로 연결을 확인하고, 위 역할에 맞게 원격을 설정합니다. `Puding` 브랜치가 이미 있으면 `git switch Puding`으로 이동합니다.

**원본이 비어 있는 경우:** `main`이 없어 pull할 수 없으므로, 운영진이 원본에 첫 커밋과 `main`을 만든 뒤 fork와 기준 브랜치를 맞춥니다. 로컬 브랜치 이름만 바꾸어서는 해결되지 않습니다.

### 주차별 작업

진행 중인 작업을 먼저 커밋하거나 따로 보관합니다. `Puding` 브랜치에서 원본의 최신 변경을 반영한 뒤 내 닉네임 폴더 안에 미션 파일을 작성합니다. 아래는 1주차 예시입니다.

```bash
git switch Puding
git fetch upstream
git merge upstream/main

# Puding/week01/ 안에서 미션을 작성한 뒤 실행
git add Puding/week01/
git diff --cached
git commit -m "FEAT: 1주차 미션 구현"
git push -u origin Puding
```

원본 변경을 반영할 때 충돌이 생기면 해결한 뒤 진행합니다.

## 커밋 메시지

```
TYPE: 요약
```

- `CHORE`: 프로젝트 생성, 설정, 패키지 추가, 파일 이동 · 복사
- `FEAT`: 새 화면, 새 기능, 새 API
- `FIX`: 버그나 잘못된 내용 수정
- `DOCS`: README, 학습 기록, ERD 같은 문서
- `REFACTOR`: 동작은 그대로 두고 코드 구조 개선
- `TEST`: 테스트 코드 추가 · 수정

- 타입은 위 표기의 대문자를 사용합니다.
- 요약은 한국어로, 명사형으로 끝내고 마침표를 찍지 않습니다.
- 한 커밋에는 한 가지 변경만 담습니다.

```
FEAT: 내 프로필 화면 구현
FIX: 시작 화면 주차 문구 수정
DOCS: 1주차 백엔드 ERD 설계 추가
CHORE: Front 프로젝트 생성
```

## Pull Request

- **base 저장소 / 브랜치:** 조직 원본 `DSWU-UMC-11th/UMC-11th-Puding` / `main`
- **head 저장소 / compare 브랜치:** 개인 fork / `Puding`
- **제목:** `N주차미션_닉네임`
  - 1주차 예시: `1주차미션_Puding`
- 본문에는 이번 주차의 작업 내용을 간단히 설명합니다.
- PR의 변경 파일과 커밋을 확인해 해당 미션만 포함되었는지 확인합니다.
- 리뷰와 머지는 팀 운영 방식에 따릅니다.

### 리뷰할 때

- 코멘트는 부담 없이, 이유와 함께 남깁니다.
- 꼭 고쳐야 하는 부분이 아니면 앞에 `[제안]` 을 붙여 구분합니다.
- 궁금한 점도 코멘트로 편하게 물어봅니다.

## 올리면 안 되는 파일

- API 키, 비밀번호, `.env` 같은 비밀 값
- `build/`, `.dart_tool/` 같은 빌드 결과물 (`.gitignore` 확인)
