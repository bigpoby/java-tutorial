# TXT 파일 검색 프로그램

윈도우 디렉토리를 입력받아 txt 파일의 목록을 출력하는 자바 프로그램입니다.

## 프로그램 구성

### 1. TxtFileFinder.java
- 기본 버전: 지정된 디렉토리에서만 txt 파일을 검색
- 파일 이름, 크기, 수정일 정보 표시

### 2. TxtFileFinderRecursive.java
- 고급 버전: 하위 디렉토리까지 재귀적으로 검색 가능
- 사용자가 재귀 검색 여부를 선택할 수 있음
- 전체 경로와 파일 정보 표시

## 컴파일 및 실행 방법

### 1. 컴파일
```powershell
# 기본 버전 컴파일
javac -d . src/TxtFileFinder.java

# 재귀 검색 버전 컴파일
javac -d . src/TxtFileFinderRecursive.java
```

### 2. 실행
```powershell
# 기본 버전 실행
java TxtFileFinder

# 재귀 검색 버전 실행
java TxtFileFinderRecursive
```

## 사용법

1. 프로그램을 실행하면 디렉토리 경로 입력을 요구합니다.
2. 윈도우 경로를 입력합니다 (예: `C:\Users\Username\Documents`)
3. 재귀 검색 버전의 경우, 하위 디렉토리 검색 여부를 선택합니다.
4. 검색 결과가 출력됩니다.

## 주요 기능

- 디렉토리 유효성 검사
- txt 파일 확장자 검사 (대소문자 구분 없음)
- 파일 정보 표시 (크기, 수정일)
- 오류 처리 및 사용자 친화적 메시지

## 예시 출력

```
=== TXT 파일 검색 프로그램 ===
검색할 디렉토리 경로를 입력하세요: C:\temp

=== 검색 결과 ===
검색 디렉토리: C:\temp
발견된 txt 파일 개수: 3

txt 파일 목록:
1. document.txt (크기: 1024 bytes, 수정일: Thu Jul 24 10:30:45 KST 2025)
2. notes.txt (크기: 512 bytes, 수정일: Thu Jul 24 09:15:30 KST 2025)
3. readme.txt (크기: 2048 bytes, 수정일: Thu Jul 24 11:45:20 KST 2025)
```
