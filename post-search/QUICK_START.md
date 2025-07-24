# 🚀 Quick Start Guide

## 빌드 & 실행 (1분 완성)

### 1️⃣ 컴파일
```bash
javac -d . src/*.java
```

### 2️⃣ 실행
```bash
java TxtWordSearcher
```

### 3️⃣ 사용
1. **디렉토리 입력**: `C:\your\directory\path`
2. **재귀 검색**: `y` (하위폴더 포함) 또는 `n` (현재폴더만)
3. **검색어 입력**: 찾고싶은 단어
4. **저장 여부**: `y` (파일저장) 또는 `n` (화면만 보기)
5. **성능 확인**: `y` (통계보기) 또는 `n` (종료)

## ⚡ 다른 실행 옵션

### 파일 목록만 보기
```bash
java TxtFileFinder              # 현재 폴더만
java TxtFileFinderRecursive     # 재귀 검색 옵션
```

## 📁 결과 파일
- 검색 결과는 `검색어_날짜.txt` 형식으로 저장
- UTF-8 인코딩
- 성능 통계 포함

## 🎯 사용 예시
```
디렉토리: C:\Projects\MyApp
재귀: y
검색어: TODO
저장: y
파일명: project_todos
→ 결과: project_todos.txt 파일 생성
```

---
**더 자세한 설명은 [README.md](README.md)를 참고하세요.**
