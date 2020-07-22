 # to2.kr/brX

# 캐릭터SET 설정 (제일 먼저 작성해주어야 하는 쿼리문!)
SET NAMES utf8mb4;

#DB 생성
# DROP DATABASE IF EXISTS site24;
# CREATE DATABASE site24;
# USE site24;

#카테고리 테이블 생성
# DROP TABLE IF EXISTS cateItem;
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
`name` = 'IT : java, jsp';

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
# DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` LONGTEXT NOT NULL
);

# 회원 테이블 생성
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`   (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL UNIQUE ,
    `name` CHAR(100) NOT NULL,
    nickname CHAR(100) NOT NULL UNIQUE ,
    loginPw CHAR(100) NOT NULL,
    email CHAR(100) NOT NULL,
    `level` INT(1) UNSIGNED DEFAULT 0 NOT NULL
    );


SELECT *
FROM article;

# TRUNCATE article;


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


SELECT *
FROM article;



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
cateItemId = 4,
displayStatus = 1,
`title` = "SQL 문법의 종류 🙄",
`body` = "# SQL 문법은 크게는 3가지의 종류로 나누어지며, 종류마다 정의는 아래와 같다.
#### DDL(Data Definition Language, 데이터 정의 언어)
* 각 릴레이션을 정의하기 위해 사용하는 언어이다.(CREATE, ALTER, DROP......)

#### DML(Data Manipulation Lanuage, 데이터 조작 언어)
* 데이터를 추가/수정/삭제하기 위한, 즉 데이터 관리를 위한 언어이다.(SELECT, INSERT, UPDATE....)

#### DCL(Data Control Language, 데이터 제어 언어)
* 사용자 관리 및 사용자별로 릴레이션 또는 데이터를 관리하고 접근하는 권한을 다루기 위한 언어이다.
(GRANT, REVOKE....)";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 5,
displayStatus = 1,
`title` = "# 라이브러리란? - What is Library? 🤔",
`body` = "# 간략 설명: 프로그램 제작 시 필요한 기능
# 비교 설명: 자동차 바퀴, 자동차 헤드라이트, 자동차 에어백
* 재사용이 필요한 기능으로 반복적인 코드 작성을 없애기 위해 언제든지 필요한 곳에서 호출하여 사용할 수 있도록 Class나 Function으로 만들어진 것입니다.
사용 여부는 코드 작성자 선택 사항이며 새로운 라이브러리 제작 시에도 엄격한 규칙이 존재하지 않습니다. 제작 의도에 맞게 작성하면 됩니다.";

SELECT * 
FROM article;


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 2,
displayStatus = 1,
`title` = "# 게시물 작성, 수정, 삭제 기능 보완",
`body` = "# # 게시물 작성, 수정, 삭제 기능을 조금이나마 개선했다. 
### 게시물 작성
1. 기존 게시물 작성 시, 부여되는 id의 오류를 개선했음(ex. 1번 다음 3번,  5번 다음 --- 7번 null 값으로 게시물이 생성되었었음)
2. 게시물 작성을 완료하고 버튼을 누르면 '게시물 작성 성공!'이라는 페이지로 넘어간 후, 뒤로가기 버튼을 눌러서 게시물 페이지로 
다시 이동하게 수정했음. (이전에는 게시물 작성 후 저장, 작성완료 버튼을 눌러야 insert 쿼리를 db에 전달할 수 있었음)

### 게시물 수정
1. 게시물 상세보기에서 수정 버튼을 누르면 게시물 수정 화면으로 이동한다.
2. 이동하면 이전에 작성했던 제목, 내용을 그대로 불러와 검토 후 수정할 수 있다. (이전에는 제목, 내용을 제대로 불러오지 못했음)
3. 마크다운 문법의 내용도 문제없이 불러올 수 있다.
4. 저장버튼을 누르면 '게시물 수정 성공!' 이라는 페이지로 이동한 후 뒤로가기 버튼으로 수정한 게시물 상세보기로 이동한다. 
5. 수정 후 저장 버튼을 누르지 않고, 다른 메뉴로 이동하거나 뒤로가기 버튼을 눌러도 기존 게시물 데이터에는 영향이 없도록 개선 완료.
(수정을 하던 안하던 input을 통해서 url에 값을 넘겨주고 받아서 다시 저장하는 과정에서 저장 외의 이동이 있는 경우 url의 값을 parameter로 받아오는데 #으로 시작하는 마크다운 문법의 문장을 문자로 인식하지 못했음. title 또는 body가 null 값이라면 해당 게시물의 id로 article 객체를 불러와 기존 title, body를 그대로 저장하게 하려고 했는데 아무리 해도 반응 없었음. 여러 테스트 결과 빈값의 title, body는 null 값이 아닌 문자길이가 0인 문자? 문장이였음. 그래서 문자길이가 0이라면 해당 게시물의 기존 데이터를 저장하도록 했음. 이렇게하니 수정 메뉴에서 다른 페이지로 이동을 무작위로 하여도 게시물 데이터에 영향을 주지 않았음)

### 게시물 삭제
1. 기존에는 게시물 상세보기에서 삭제버튼을 누르면 해당 게시물 id를 url로 넘겨  parameter로 받아서 sql에 해당 게시물 삭제를 요청했다. 
2. 게시물 삭제를하고 게시물이 존재하지 않아 오류가 났었나.. 삭제된 게시물의 상세페이지에 그대로 남아있었나 기억이 명확하지 않지만 삭제 후 페이지의 이동 경로?가 마음에 들지 않아 방법을 고민했다.
3. 고민 당시 delete.jsp에 연결을하고 저번주에 샘한테 팁을 얻었던 방법으로 아래 코드로 게시물 리스트 페이지로 바로 이동할 수 있도록 만들었었다.
```javascript
<script>location.replace(${pageContext.request.contextPath}/s/article/list</script>
```
4. 그러나 오늘 또 이 방법이 마음에 들지 않아 상기 코드를 삭제하면서 해당 게시물의 cateItemId를 url로 받아 '게시물 삭제 성공!' 페이지를 확인한 후, 뒤로가기 버튼을 누르면 해당 cateItemId의 게시물 리스트로 돌아가도록 만들어두었다.";





INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 3,
displayStatus = 1,
`title` = "# [ HTML ] form의 method 타입 get, post 테스트 결과",
`body` = "## form이 뭔지도 몰랐다.
get과 post 2가지 타입을 모두 테스트해보았다. 
물론 의도한바는 아니지만 😞
한번에 성공했어도 좋았겠지만 2가지의 경우를 모두 경험해볼 수 있어서 뜻깊은 시간이었다고 생각한다.

#### 일단 지금도 명확하게 알지는 못하지만. 
### get 과 post 의 명확한 차이는 존재한다.


## `get : 긴 글을 전송하지 못한다. `
아무리 시도해도 전송되지 않는다.  글을 정리해서 올리려고 했는데 전송이 되지 않는다... 망한 것이다.  망.... 
### java.lang.IllegalArgumentException: 요청 헤더가 너무 큽니다.
라는 오류를 출력해낸다.
미흡한 코드겠지만 그래도 기능이 다듬어지고 있다고 생각해서 기분 좋게 공부하고 있었는데 망연자실했다. 💀

### 그리고 생각했다.  '아.. 답은 post인가... ' 
처음에 post를 먼저 시도했고, 분명 DB로 데이터가 전송되어 클라이언트한테까지 전달이 되는거 같았는데 한글이 깨져버렸다.
그래서 또 한참을 찾다가 tomcat의 server.xml에 URIEncoding=""utf-8"" 이 코드를 추가해보기도 하고 jsp file에 또 다른 코드를 추가해보기도 하고...  그렇지만 계속 깨짐. 그래서 결국 1-2시간 시도하다가 get 타입으로 갈아탄 것.

#### post로 전송을하면 뭔가 다른 방식으로 데이터를 받아야 한다고 생각했다.
자료를 찾아봐도 보이는것은 javascript뿐... 난 아직 그것을 모르네만 
그리고나서 찾은 java 코드들은 잉?... 한번도 본적 없는... 
그래서 너무 어렵게 느껴져서 get을 어떻게든 써보려고 시도했으나 결론은
### `post 가 답이다` 로 다시 자료를 검색하기 시작.
또 다시 이런저런 코드를 넣어보고 다시 빼고, 반복하다가 운이 좋게 완벽한 코드 발견. 
### request.setCharacterEncoding(""UTF-8"");  🙉   감격.
### response.setContentType(""text/html; charset=UTF-8""); 밑에 써주었다.

## 작동 아주 잘된다 굿👍
### 테스트 용으로 긴글을 아주아주 장문의 글을 복붙복붙해서 게시글 작성을 해보니
### 아주 잘 전송되어 저장된다🍺



그리고 
# enctype 
#### 1. application/www-form-urlencoded  
* 디폴트값이다. enctype을 따로 설정하지 않으면 이 값이 설정된다. 폼데이터는 서버로 전송되기 전에 URL-Encode 된다.
#### 2. multipart/form-data
* 파일이나 이미지를 서버로 전송할 경우 이 방식을 사용한다.
#### 3. text/plain
* 이 형식은 인코딩을 하지 않은 문자 상태로 전송한다.



### 현재 나의 enctype은 디폴트값인 application/www-form-urlencoded 이고 아까 낮까지는 뭣도 모르고 multipart/form-data 타입으로 text를 전송하려고 했다🙋‍


### 끝";



INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 4,
displayStatus = 1,
`title` = "# SQL 기초 강좌 리스트",
`body` = "# SQL 기초 강좌 리스트 🕵‍
- [SQL 기초 강좌, 1강] SQL이란 무엇인가?
- [SQL 기초 강좌, 2강] DB 관리를 위한 툴 사용법
- [SQL 기초 강좌, 3강] 테이블 생성, 수정, 삭제
- [SQL 기초 강좌, 4강] 데이터 조회
- [SQL 기초 강좌, 5강] 데이터 생성, 수정, 삭제


* 이미지는 git으로 첨부? 이용해야 한다.
이미지 첨부, 사용하는법 알아보기(오늘(2020-07-07)나만의 숙제))";



SELECT *
FROM article;



INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 4,
displayStatus = 1,
`title` = "# [SQL 기초 강좌, 1강] SQL이란 무엇인가?  🙄",
`body` = "# [SQL 기초 강좌, 1강] SQL이란 무엇인가?  🙄

## SQL
은 Structured Query Language(구조적 질의 언어)의 줄임말로, 관계형 데이터베이스 시스템에서(RDBMS)에서 자료를 관리 및 처리하기 위해 설계된 언어입니다. SQL은 1970년대에 IBM에서 최초 개발되었으며 관계형 모델이라는 이론에서 파생된 특징을 가지고 있는데, 현재 SQL의 표준으로 ANSI SQL이 정립되었습니다. 각 `DBMS` 프로그램에서 ANSI SQL을 기반으로 개발된 개별 SQL을 사용하며 서로 근소한 차이를 보입니다.

## DBMS
DBMS란 DataBase Management System의 줄임말로, 컴퓨터에 저장된 데이터베이스를 관리해주는 소프트웨어를 지징합니다.
만약 데이터를 파일 단위로 직접 관리한다면 수십여 건 정도는 각자의 노하우를 기반으로 무리 없이 사용할 수 있습니다.
하지만, 데이터 수가 증가하여 관리해야 할 파일 또한 늘어나게 된다면 분명히 언젠가 한계에 부딪힐 것은 자명한 사실입니다.
때문에 DBMS를 활용하여 데이터를 데이터베이스로 정리하여 좀 더 효율적으로 데이터를 통제하고 관리하게 되었습니다.

DBMS는 1960년대의 계층형 DB를 사용하는 HDBMS인 IMS가 최초로 출시된 이래, 네트워크형, 관계형, 객체형 등으로 진화를 거듭해오고 있습니다.
현재 출시된 DBMS의 종류는 매우 다양하며, 현재에는 관계형 DBMS(RDBMS)를 주로 사용중에 있습니다. 대표적인 RDBMS에는 Oracle, MySQL(MariaDB), PostgreSQL, Server, IBM DB/2 등이 있으며, 각각의 RDBMS는 서로 다른 특징과 장단점을 가지고 있습니다.
본 강좌에서는 MySQl(MariaDB)을 사용할 예정입니다. MariaDB를 사용하기 위한 준비 과정은 다음 강좌에서 소개해드리도록 하겠습니다.




### SQL 문
* SQL문은 대소문자 구분을 하지 않습니다. select는 SELECT와 같은 명령입니다.
* 각각의 SQL문 끝에 `;`를 붙여서 서로를 구분합니다. 

# 가장 중요한 기본적인 SQL 명령어
1. SELECT : 데이터베이스에서 데이터 추출
2. INSERT INTO : 데이터베이스에 새로운 데이터 삽입
3. UPDATE : 데이터베이스의 데이터 갱신
4. DELETE : 데이터 삭제
5. CREATE DATABASE : 새로운 데이터베이스 생성
6. ALTER DATABASE : 데이터베이스 변경
7. CREATE TABLE : 테이블 생성
8. ALTER TABLE : 테이블 변경
9. DROP TABLE : 테이블 삭제
10. CREATE INDEX : 인덱스 생성
11. DROP INDEX : 인덱스 삭제


# 쿼리문 둘러보기
##### 테이블 구조 참조하기(DESC)
```sql
DESC 테이블명;
```

##### 검색 조건 지정하기(WHERE)
```sql
SELECT 열1, 열2 FROM 테이블명 WHERE no = 2;
```
* no열의 값이 2인 경우만 조회
```sql
SELECT * FROM 테이블명 WHERE no <> 2; 
```
* no열의 값이 2이 아닌 경우만 조회
```sql
SELECT * FROM 테이블명 WHERE name='홍길동';
```
* name열이 홍길동인 경우만 조회, 숫자가 아닌 문자열이나 날짜에 경우 '' 싱글 쿼트롤 둘러싼다.
```sql
SELECT * FROM 테이블명 WHERE name IS NULL;
```
* name 열이 NULL인 경우만 조회

##### WHERE 절 조건 조합하기
```sql
SELECT * FROM 테이블명 WHERE 조건1 AND 조건2;
SELECT * FROM 테이블명 WHERE 조건1 OR 조건2;
SELECT * FROM 테이블명 WHERE NOT 조건;
```
* AND는 OR에 비해 우선순위가 높다. 그러므로 괄호를 통해서 우선수위를 바꿀 수 있다.
```sql
SELECT * FROM 테이블명 WHERE (a=1 OR a=2) AND (b=1 OR b=2);
```


##### 패턴매칭에 의한 검색
```sql
SELECT * FROM 테이블명 WHERE text LIKE 'SQL%';
```
* text라는 열에서 SQL로 시작하는 내용이 있다면 검색한다. (전방매치)
```sql
SELECT * FROM 테이블명 WHERE text LIKE '%SQL%';
```
* text라는 열에서 SQL을 포함하는 내용이 있다면 검색한다. (중간매치)
* 예를들어 'SQL은 RDBMS를 조작하는 언어이다'
* 'LIKE는 SQL에서 사용할 수있는 술어중 하나이다'

```sql
SELECT * FROM 테이블명 WHERE text LIKE '%\%%';
```
* 이스케이프를 통해서 % 검색하기
* _를 검색할떄도 이스케이프 (\_) 시켜야한다. 


# 상기 외 공부하면 좋은 내용들
1. COUNT로 행 갯수 구하기
2. DISTINCT로 중복 제거하기
3. SUM으로 합계 구하기
4. AVG로 평균내기
5. MIN, MAX로 최솟값, 최댓값 구하기
6. GROUP BY로 그룹화하기
7. HAVING 구로 '집계함수'의 조건걸기
8. 서브쿼리
9. 클라이언트 변수
10. 스칼라 값
11. EXISTS (데이터 존재 유무 확인)
12. 상관 서브쿼리
13. IN (집합간의 비교하기)
14. 제약 조건 선언, 추가, 삭제

";


SELECT * 
FROM article;


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "# 드디어 첫 이미지 게시! 👨‍🚀",
`body` = "![image](https://user-images.githubusercontent.com/63379459/86793677-a8f5b300-c0a6-11ea-9aa6-6bbf5123972a.png)

### 이미지 게시를 할 수 있어서 몹시 기분이 좋다 🤠";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 3,
displayStatus = 1,
`title` = "# [ HTML ] form method=""post"" TEST 결과",
`body` = "![image](https://user-images.githubusercontent.com/63379459/86795422-8369a900-c0a8-11ea-95e0-c130a1572519.png)

# get 방식처럼 URL에 값을 넘겨주고 parameter로 받아야 하는 줄 알았다.

### 그러나 실험 결과, 각 name의 결과 값을 URL에 넘기지 않고 submit을 통해서 아직....  구체적으로 이해되지 않는...   흐름으로 자바에 넘겨지는 듯 하다.

### 일단 게시물 작성 jsp에서 value에 ${param.title},  ${param.body}를 빼주었다. 문제없이 게시물이 저장되었다.  😯";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "# 2020-07-08 오늘의 숙제.....😱😱😱😱😱😱😱😱",
`body` = "# 혜련 블로그 구조 개선........... 

1. scale : 리스트에서 밑에 게시물 리스트 영향 받지 않도록 창이 커지도록??? 시도해보기.
2. 상세보기를 했을 때, URL에 id만 입력해도 글이 나오도록(현재 cateItemId까지 받도록해서 id만 입력하면 
상세보기를 불러오지 않음 _ 오류 발생)
## 1번의 해결책
- CSS scale에 대해서 조사, 코드 반영
## 2번의 해결책
- URL에 cateItemId를 빼고 상세보기 이동, 수정, 목록, 삭제의 버튼을 눌렀을 때 URL로 받아오던 값들을 다른 방법으로 받아서 처리할 수 있도록 코드를 뜯어고쳐야함 😭😭😭😭😭😭😭😭😭😭😭😭😭";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 5,
displayStatus = 1,
`title` = "# 앞으로 공부 계획(종강까지)",
`body` = "# 계획
* JSP/서블릿 블로그[50%]
 - 게시물 리스팅
 - 게시물 상세페이지
 - 블로그, 카테고리 분류 기능
 - 검색기능
 - 페이징 기능
 - SQL 오류발생시 즉시 꺼짐 (오류 출력)

* 스프링부트 커뮤니티 사이트[0%]
* 앵귤러 시작[0%]
- 타입스트립트 기초
 - https://typescript-kr.github.io/pages/tutorials/typescript-in-5-minutes.html
- 앵귤러 기초
 - https://angular.kr/start


---------------------------------------------------------------------------------------------------------------------

1. 블로그 
2. 스프링  커뮤니티 (소통하는?) 비번 물어보면... 정보? 메일로 보내주는?? (하면 취업에 좋음)
3. 앵귤러 시작 -> .... 어려우면 하지 않아도 되지만 알아보기

★ 일단, 블로그와 스프링부트 커뮤니티 사이트는 꼭 할 수 있도록.

★ 스프링, 스프링부트 차이
1. 스프링부트 : 스프링을 쉽게 사용할 수 있는 도구 같은????
2. 스프링 사용하려면 조합해야함... 외워야하고 써야할 부분이 많은 작업
-> 그런것을 해주는게 스프링부트.

회사에가면 스프링, 스프링부트 둘 중에 하나는 할 것이다.

목표 :  최소한 커뮤니티 사이르를 2개 가지고 갈거다.  블로그 1개 


여력이 된다면 앱 만들 계획(샘 계획)
-> 비대면 연기 오디션. 영화 배우. 3분 영상 업로드. 관리자 검토. off-line 면접보자.... 라는 앱을 앵귤러로 진행 예정. 

★ 앵귤러는 많이 어렵다.   할 수 있으면하고, 어렵다면 패스..해도 좋지만 할 수 있도록....!!!하자..... 
앵귤러는 구글이 각을 잡고 만든다? react의 경우... 붙이고 붙이고?  앵귤러는 다 있다. ... 설정하는데 있어서 훨씬 좋다.... ";




SELECT * 
FROM article;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "# 회원가입 폼 만들기",
`body` = "# 회원가입 폼 만들기 참고 자료 


## onsubmit 의미 : submit버튼을 누르면 onsubmit이 실행되어 특정함수가 실행되고 특정함수의 return 값이 true일 경우에만 폼을 전송한다.

## submitJoinForm(this) 의미 : this는 특정 객체를 말하는 것으로 이 form 태그의 form을 가리킨다.

<img width="897" alt="onsubmit1" src="https://USER-images.githubusercontent.com/63379459/87073341-33c9df80-c258-11ea-8a5b-ffc12a19c29d.PNG">

<img width="841" alt="onsubmit2" src="https://USER-images.githubusercontent.com/63379459/87073360-3cbab100-c258-11ea-8c1b-36e7d750da55.PNG">

* 버튼을 누를때마다 함수가 실행되지만 return false이므로 데이터가 전송되지 않는다.
<img width="831" alt="onsubmit5" src="https://USER-images.githubusercontent.com/63379459/87073382-45ab8280-c258-11ea-850f-25503ea52f33.PNG">

* form.loginId.value 의미 : form(this)의 name인 loginId의 값을 의미.
* trim()  혹시 모를 앞뒤 공백을 제거(space만 입력한것을 잡아낸다)
* 로그인 아이디가 제대로 입력되지 않아 오류메세지를 받는 경우 커서를 loginId창에서 깜빡거리게 하기 위해서는 form.loginId.focus(); 를 입력해주면 된다.

<img width="2201" alt="submit6" src="https://USER-images.githubusercontent.com/63379459/87073401-4e03bd80-c258-11ea-90d4-9d41bba819d9.png">
* 중간에 if~라면 return; 넘어가지않고 여기서 끝내겠다.
* 마지막에 form.submit();    마지막까지 입력이 제대로 되었다면 함수를 호출한 태그인 form을 제출하겠다. 

<img width="908" alt="submit7" src="https://USER-images.githubusercontent.com/63379459/87073420-565bf880-c258-11ea-9a71-cf217df8fadd.PNG">

* indexOf(' ') != -1 의미 : 글자 사이에 공백이 들어있다..

## 우리나라 법률상 패스워드를 원문으로 데이터 전송하는것은 좋지 않다. 
* 패스워드를 암호화해야 한다.

### 계획 : 자바스크립트 수준에서 직접 암호화를 한 다음 데이터 전송

### 암호화 방법 2가지 (복호화 : 암호 해석)
1. 복호화 가능
2. 복호화 불가능 
* 복호화가 불가능한 방법으로 해야 한다. 
#### hash 함수 사용 ( 그 중에서도 sha256 )
1. cdnjs.com 접속
2. js-sha256 검색
3. Link 복사 
<img width="870" alt="submit8" src="https://USER-images.githubusercontent.com/63379459/87073442-607df700-c258-11ea-8d96-6f441c475d3d.PNG">
* script src를 통해서 불러온다.";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 4,
displayStatus = 1,
`title` = "# 조건에 부합하는 10보다 작은 번호 SELECT",
`body` = "# SQL 쿼리문 

```sql

SELECT id 
FROM article 
WHERE id < 10 
AND displayStatus =1 
AND cateItemId = 3 
ORDER BY id ASC 
LIMIT 1

``` ";

# 조회수 칼럼 추가
ALTER TABLE article ADD COLUMN hit INT(10) UNSIGNED NOT NULL AFTER `body`;
# ALTER TABLE article ADD COLUMN hit INT(10) UNSIGNED NOT NULL DEFAULT 0 AFTER `body`;

DESC article;

# member field 추가 (updateDate)
ALTER TABLE MEMBER ADD COLUMN updateDate DATETIME NOT NULL AFTER regDate;

# member field 추가 (email)
ALTER TABLE `member` ADD COLUMN email CHAR(200) NOT NULL AFTER loginPwReal;

# article table에 field 추가(memberId)
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER hit;

CREATE TABLE articleReply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    articleId INT(10) UNSIGNED NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL
);

DESC article;

# member loginPwReal을 loginPw로 변경(혜련 실수)
ALTER TABLE `member` CHANGE loginPwReal loginPw CHAR(255) NOT NULL;

# level 칼럼 추가 
ALTER TABLE `member` ADD COLUMN `level` INT(1) UNSIGNED NOT NULL AFTER email;

# 기존 게시물의 작성자 번호를 1번으로 정리(통일)하는 쿼리 
# 의미 : article 테이블의 memberId가 0인것만 1로 set한다. 
UPDATE article
SET memberId = 1
WHERE memberId = 0;

SELECT *
FROM article;