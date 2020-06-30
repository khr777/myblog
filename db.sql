# to2.kr/brX

# 캐릭터SET 설정
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

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
`title` = "이 블로그는 제가 직접 개발한 블로그입니다.",
`body` = "";