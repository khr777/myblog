# to2.kr/brX

# 캐릭터SET 설정 (제일 먼저 작성해주어야 하는 쿼리문!)
SET NAMES utf8mb4;

#DB 생성
DROP DATABASE IF EXISTS site24;
CREATE DATABASE site24;
USE site24;

#카테고리 테이블 생성
DROP TABLE IF EXISTS cateItem;
CREATE TABLE cateItem (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE
);

# 카테고리 아이템 4~5개 
INSERT INTO cateItem
SET id = 1,
regDate = NOW(),
`name` = '일상';

INSERT INTO cateItem
SET id = 2,
regDate = NOW(),
`name` = 'IT : java';

INSERT INTO cateItem
SET id = 3,
regDate = NOW(),
`name` = 'IT : html/css/js';

INSERT INTO cateItem
SET id = 4,
regDate = NOW(),
`name` = 'IT : sql';

INSERT INTO cateItem
SET id = 5,
regDate = NOW(),
`name` = 'IT 기타';

INSERT INTO cateItem
SET id = 6,
regDate = NOW(),
`name` = '이거저거';

SELECT *
FROM cateItem;

# 게시물 테이블 생성
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` TEXT NOT NULL
);

SELECT *
FROM article;

TRUNCATE article;


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "이 블로그는 제가 직접 개발한 블로그입니다.",
`body` = "# 개발 공부 시작 시기
2020-04-29 부터 현재까지 SBS아카데미 학원에서 프로그래밍 언어를 공부하고 있습니다.

## 2020-06-30 화요일부터 구현 중이던 블로그의 도메인 주소를 선물 받아 관리를 시작하게 되었습니다.

#### 앞으로 보완해야 하는 기능
- 페이징
- 검색
- 댓글
- 조회수
- 수정
- 삭제
- 반응형 구현
- 카테고리
- 등등

## 남은 교육기간 동안 초심을 잃지 않고 열심히 공부해서 후회없는 나날들을 지내보도록 하겠습니다.🤠

# 나는 할 수 있다 !! 아자아자 !!! 💪";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "이 블로그는 제가 직접 개발한 블로그입니다.",
`body` = "# 무료 이미지 정보 사이트 
- 픽사베이
###### https://pixabay.com/ko/ 

# 무료 이모티콘 정보 사이트
- 이모지키보드
###### https://www.emojiengine.com/ko/
- 폰트어썸 
###### https://fontawesome.com/

# 페이스북, 트위터, 게시판 및 블로그에 게시할 수 있는 이미지 영구적인 링크만드는 사이트
- 포스트이미지
###### https://postimages.org/

# 개발자 블로그 마케팅 참고 사이트
- 벨로그
###### https://velog.io/

# 제이쿼리 라이브러리 제공해주는 사이트
###### https://cdnjs.com/

";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "웹 개발에 참고하기 좋은 사이트 정보 모음집.",
`body` = "# 무료 이미지 정보 사이트 
- 픽사베이
###### https://pixabay.com/ko/ 

# 무료 이모티콘 정보 사이트
- 이모지키보드
###### https://www.emojiengine.com/ko/
- 폰트어썸 
###### https://fontawesome.com/

# 페이스북, 트위터, 게시판 및 블로그에 게시할 수 있는 이미지 영구적인 링크만드는 사이트
- 포스트이미지
###### https://postimages.org/

# 개발자 블로그 마케팅 참고 사이트
- 벨로그
###### https://velog.io/

# 제이쿼리 라이브러리 제공해주는 사이트
###### https://cdnjs.com/

";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "SQL 기초 문법",
`body` = "## 명령어 실행 
- ctrl + 9 

## 관리하고 있는 모든 데이터베이스 확인
```sql 
SHOW DATABASES;
```

## blog라는 임의 데이터명으로 데이터 베이스 생성
```sql
CREATE DATABASE `blog`;
```
-  DATABASE 이름은 ``(숫자 1 옆에 있는 기호)으로 감싸주는 것이 '정석'이다. 사용하지 않아도 문제는 없다.
-  DATABASE를 생성하고 좌측 리스트에 확인이 되지 않는 경우, 계정 위에 마우스를 올리고 F5를 눌러야 리스트업 된다.

## blog 데이터 베이스 삭제
```sql
DROP DATABASE blog;
```

## 테이블 리스팅
```sql
SHOW TABLES;
```

## 특정 테이블의 구조
```sql
DESCRIBE article;
```
- DESC article; 이라고해도 무관

## 기존에 blog 데이터 베이스가 존재한다면 삭제
```sql
DROP DATABASE IF EXISTS blog;
```

## 사용하려는 데이터베이스 선택(다른 데이터베이스와 구분해서 사용하기)
```sql
USE blog;
```

## 용어 설명, 이해
- VARCHAR(100) : 알파벳은 100개 저장 가능. 한글은 한글자에 2~3바이트로 약 33~50글자 정도 담을 수 있다.
- CHAR와 VARCHAR의 용량 소모하는 바이트는 똑같지만 VARCHAR는 용량을 아끼려는 녀석이고 CHAR는 용량을 아끼려하지 않지만 속도가 훨씬 빠르다.
- TEXT : 긴 문자 수용 가능
- LONGTEXT : 정말 긴 문자는 TEXT로 수용할 수 없어 LONGTEXT를 사용해야 한다.

## 오류 중, You have an error in your SQL syntax; 발생 사유
- 이런 문법은 없다. 문법 오류이니 확인하고 다시 입력해달라는 의미.

## 데이터 추가
```sql
INSERT INTO article
SET title = '제목입니다.',
`body` = '내용입니다.';
```
- 테이블 구조에 맞춰서 자동 증가하는 id를 제외하고는 모두 입력해주어야 한다. (NOT NULL을 지정한 경우, 입력하지 않으면 오류가 발생한다.

## 데이터 조회(1)
```sql
SELECT *
FROM article;
```
- article 테이블의 모든 데이터를 조회하겠다.

## 데이터 조회(2)
```sql
SELECT title, `body`
FROM article;
```
- article 테이블 구조 중 title과 body 정보를 조회하겠다.

";

SELECT *
FROM article;


