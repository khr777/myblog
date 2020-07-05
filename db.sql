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
SET regDate =NOW(),
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
cateItemId = 5,
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
SET regDate =NOW(),
updateDate = NOW(),
cateItemId = 4,
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



INSERT INTO article
SET regDate =NOW(),
updateDate = NOW(),
cateItemId = 2,
displayStatus = 1,
`title` = "Servlet 기초",
`body` = "# @WebServlet(/s/별 )
servlet경로 중에 없는 경로로 진입하면 
이 DispatcherServlet이 받아온다.

###### 모든 경로를 빨아들이는 블랙홀 같은 녀석이다. 

입력한 servlet의 주소가 존재한다면 해당 주소로 진입이 되겠지.

#### 예시 
- /s/article/list  -> request 라고 한다.
(request 중에 하나이다.)
(동일한 경로의 servlet이 없는 경우)

### 구현 방법
- String requestURI = req(uest).getRequestURI();

## 참고 사항
- alt + shift + r : servlet의 request, response를 짧게 변경할 수 있다.  그리고 같은 공간에 있는 같은 변수명을 동시에 변경할 수 있다.
- ";



INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 2,
displayStatus = 1,
`title` = "블로그 관리 이번주 수정 계획",
`body` = "카테 3";



SELECT *
FROM article;

SELECT COUNT(*) 
FROM article
WHERE cateItemid = 2;


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 3,
displayStatus = 1,
`title` = "카테 3",
`body` = "카테 3";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 2,
displayStatus = 1,
`title` = "블로그 관리 이번주 수정 계획",
`body` = "# 블로그 관리 수정하고 싶은 기능

###### 1. 게시물 상세보기에 목록 버튼을 누르면 카테고리로 이동하기 전 리스트(예시: IT:java 리스트)로 이동하게 하기.

-  현재 문제(이유) : 게시물 상세보기에 게시물 리스트를 볼 수 있는 '목록'이라는 버튼이 있지만 이 버튼을 사용할 경우 카테고리를 타고 들어가기 전 리스트가 아닌 최신글을 보여주는 리스트로 이동함.

- 계획 : 카테고리를 통해서 카테고리 리스트로 이동했을 경우, 카테고리의 번호를 남겨주고 파라미터로 받아서 목록을 누르면 그 주소로 이동하도록 해야 할지 생각 중.

###### 2. 게시물 리스트 왼쪽 여백 채워보기

###### 3. aboutMe 여백과 조금 더 예쁘게 할 수 있을지 구상하기.

###### 4. 게시물 상세보기  editor 너비 줄이기.

###### 5. 게시물 상세보기의 번호, 등록일, 작성자 등 살짝 바꿔보기... 너무 밋밋함.

###### 6. 게시물 상세보기의 여백 구상(깔끔하지 못할 것 같으면 여백 남겨 두기)";

SELECT * 
FROM article;


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 3,
displayStatus = 1,
`title` = "sql에 저장된 값을 불러올 때(확인할 때) 참고할 사항",
`body` = "# java에서 db 정보를 SELECT 해올 때는
## executeQuery(sql) 을 사용한다. 
`예시`
```java
List<Map<String, Object>> rows = new ArrayList<>();


Statement stmt = null;
ResultSet rs = null;



try {
    stmt = connection.createStatement();
    rs = stmt.executeQuery(sql);
    ResultSetMetaData metaData = rs.getMetaData();
    int columnSize = metaData.getColumnCount();

    while (rs.next()) {
        Map<String, Object> row = new HashMap<>();

        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            String columnName = metaData.getColumnName(columnIndex + 1);
            Object value = rs.getObject(columnName);

            if (value instanceof Long) {
                int numValue = (int) (long) value;
                row.put(columnName, numValue);
            } else if (value instanceof Timestamp) {
                String dateValue = value.toString();
                dateValue = dateValue.substring(0, dateValue.length() - 2);
                row.put(columnName, dateValue);
            } else {
                row.put(columnName, value);
            }
    }

        rows.add(row);
}
```
";

SELECT *
FROM article;




INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 4,
displayStatus = 1,
`title` = "sql % 기호 사용법",
`body` = "# SQL에서 %을 그냥 사용하면 문법 오류가 발생한다.
#### %을 쿼리에 적용하기 위해서는 %을 1개 더 붙여서 작성해야 한다😵";





SELECT *
FROM article;


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 5,
displayStatus = 1,
`title` = "2020-07-03 금요일 블로그 구현 발표 준비",
`body` = "# 가능한 기능 
1. 로고 클릭하면 홈으로 이동
2. home, articles, aboute me, editor, sns(github, tistory, youtube) 클릭하면 모두 페이지 이동 가능
3. 최근에 알게된 toast ui editor를 블로그 구현, 보완, 게시물 작성을하며 자주 사용하게 되어 따로 메뉴를 만들어서 언제든 사용할 수 있도록 만들어 놓음.
4. 일단 게시물 메뉴로 이동하면 최신 게시물 10개를 리스팅
5. 게시물 메뉴로 이동하면 카테고리 메뉴가 별도로 존재
6.  해당 카테고리를 클릭하면 카테고리별로 게시물 10개씩 리스팅
8. 카테고리별로 페이지 번호 출력 가능 
9. `문제` : 아직 페이지 번호를 숨기는 기능을 구현하지 못해서 리스팅되는 페이지가 모두 출력된다..(2-30개 이상까지도)
10. 게시물 메뉴에서 주소창에 search 라는 이름을 통해서  카테고리 구분 없이 검색하려는 제목 또는 내용을 입력하면 검색어를 포함한 게시물이 게시 개수 제한없이 모두 출력된다.
11. `문제` : 나중에는 페이지에 담아 관리하고 싶다.
12.  게시물 상세보기 가능 , 목록버튼을 누르면 상세보기했던 카테고리의 게시물 목록으로 이동한다.
13.  aboutme에는 아주 간략한 자기 소개
14.  이 블로그는 반응형으로 모두 작동하여 pc에서 사용하는 모든 기능을 미흡하게나마 현재까지 구현한 기능을 동일하게 사용할 수 있음.

# 일단 제일 추가하고 싶은 기능
1. 게시물, 댓글 등 글을 블로그에서 직접 입력하고 저장하고 싶음. ";



SELECT *
FROM article;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 5,
displayStatus = 1,
`title` = "블로그에 적용하고 싶은 기능(계획)",
`body` = "# 구현하고 싶은 기능 🙈🙉
1. 도메인 주소창에 검색어를 입력하는게 아닌 검색창을 만들어서 검색창에 직접 글을 입력하고 검색어에 대한 게시물을 찾아오게 하는 기능
2. 상세보기에서 이전, 다음 버튼을 만들어 이전 상세게시물, 다음 상세게시물로 이동할 수 있는 기능.
3. 블로그에 직접 글을 입력하고 저장하고, 리스트로 불러오게 하는 기능.
4. 현재 홈 화면 이미지가 웹페이지 크기(비율)에 맞춰 자유롭게 커지고 작아지지 않음. 이 문제를 해결하고 싶음.
5. 게시물에 이미지도 함께 등록해보고 싶음.
6. 게시물 수정 기능(페이지 화면에서)
7. 게시물 삭제 기능 (페이지 화면에서)
8. 총 게시물 수 출력
9. ` 다른 기능 생각나면 마저 정리하기.`  
10. 카테고리에 공부계획 추가하기.
11. 현재 editor 메뉴를 들어가면 doWrite를 통해서 null 값의 게시물이 신규로 추가 되고 있음. 
그 문제 해결해야 함.
할 수 .. 있겠지...??!! 👻";


SELECT *
FROM article;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 5,
displayStatus = 1,
`title` = "이번주 주말 공부 계획(2020-07-04/05)",
`body` = "# 이번 주 공부 계획 🤺
1. 금요일 : 일단 오늘 영상 참고해서 내 블로그 구조에 도입하기.
2. 토요일 : 최근 블로그 관련 강의 영상 모두 처음부터 돌려보며 코드 이해하기.
3. 일요일 : 토요일에 공부해서 이해한 코드들을 활용하여 추가로 구현하고 싶은 기능 계획, 구현, 정보 찾아보기.
* 참고 : editor에 또 참고하면 좋을 사이트 링크 추가해서 활용하기.";




SELECT *
FROM article;

