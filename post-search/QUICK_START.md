# 🚀 Quick Start Guide (한글 검색 지원)

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
3. **검색어 입력**: 찾고싶은 단어 (**한글 완벽 지원!** 🇰🇷)
4. **자동 저장**: 검색 결과가 있으면 자동으로 `output/` 폴더에 저장
5. **성능 확인**: `y` (통계보기) 또는 `n` (종료)

## ⚡ 다른 실행 옵션

### 파일 목록만 보기
```bash
java TxtFileFinder              # 현재 폴더만
java TxtFileFinderRecursive     # 재귀 검색 옵션
```

## 📁 결과 파일
- **자동 저장**: `검색어_YYYYMMDD_HHmmss.txt` 형식
- **저장 위치**: `output/` 폴더에 자동 생성
- **UTF-8 인코딩으로 한글 완벽 지원**
- 성능 통계 포함

## 🎯 사용 예시

### 한글 검색 예시 🇰🇷
```
디렉토리: C:\Documents\Korean
재귀: y
검색어: 한글테스트
자동 저장됨: output/한글테스트_20250724_141500.txt
```

### 영문 검색 예시
```
디렉토리: C:\Projects\MyApp
재귀: y
검색어: TODO
자동 저장됨: output/TODO_20250724_141600.txt
```

### 한영 혼용 검색
```
검색어: 한글test, program테스트, 검색function
→ 모든 형태의 한영 혼용 텍스트 검색 가능
→ 자동 저장: output/검색어_날짜시간.txt
```

---
**더 자세한 설명은 [README.md](README.md)를 참고하세요.**
