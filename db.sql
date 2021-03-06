DROP DATABASE IF EXISTS cuni;

CREATE DATABASE cuni;

USE cuni;

# 회원
CREATE TABLE `member` (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id),
  regDate DATETIME NOT NULL,
  loginId CHAR(50) NOT NULL,
  loginPw CHAR(120) NOT NULL,
  `name` CHAR(50) NOT NULL
);

ALTER TABLE `cuni`.`member` ADD UNIQUE INDEX (`loginId`); 

INSERT INTO `member`
SET regDate = NOW(),
`name` = '홍길동',
`loginId` = 'user1',
`loginPw` = 'user1';

INSERT INTO `member`
SET regDate = NOW(),
`name` = '홍길순',
`loginId` = 'user2',
`loginPw` = 'user2';

# 게시판
CREATE TABLE board (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id),
  regDate DATETIME NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `code` CHAR(50) NOT NULL
);

ALTER TABLE board ADD INDEX (`code`);

INSERT INTO board
SET regDate = NOW(),
`name` = '공지사항',
`code` = 'notice';

INSERT INTO board
SET regDate = NOW(),
`name` = '자유게시판',
`code` = 'free';

# 게시물
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id),
    regDate DATETIME NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` CHAR(100) NOT NULL
);

INSERT INTO article
SET regDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
boardId = 1,
memberId = 2,
title = '제목3',
`body` = '내용3';

/* MySQL 사용자 추가 */
-- GRANT ALL PRIVILEGES ON cuni.* TO sbsst@`%` IDENTIFIED BY 'sbs123414';
-- GRANT ALL PRIVILEGES ON cuni.* TO sbsst@`localhost` IDENTIFIED BY 'sbs123414';