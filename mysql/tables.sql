CREATE DATABASE IF NOT EXISTS forum DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE forum.blacklist
(
    user_id int(11) PRIMARY KEY NOT NULL,
    reason varchar(64),
    free_time datetime NOT NULL
);
CREATE TABLE forum.favorite
(
    post_id int(11) NOT NULL,
    favorite_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL
);
CREATE TABLE forum.history
(
    user_id int(11) NOT NULL,
    history_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    post_id int(11) NOT NULL,
    view_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TABLE forum.hot_post
(
    post_id int(11) NOT NULL,
    rank int(11) PRIMARY KEY NOT NULL
);
CREATE TABLE forum.notice
(
    reply_id int(11) PRIMARY KEY NOT NULL,
    post_user_id int(11) NOT NULL,
    reply_who_user_id int(11) NOT NULL,
    post_user_flag tinyint(4) NOT NULL,
    reply_who_user_flag tinyint(4) NOT NULL
);
CREATE TABLE forum.post
(
    post_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title varchar(64) NOT NULL,
    like_num int(11) DEFAULT '0',
    module varchar(64) NOT NULL,
    label varchar(64) NOT NULL,
    user_id int(11) NOT NULL,
    post_time datetime DEFAULT CURRENT_TIMESTAMP ,
    file_paths varchar(255),
    post_content varchar(255),
    update_time datetime DEFAULT CURRENT_TIMESTAMP,
    reply_num int(11) DEFAULT '0'
);
CREATE TABLE forum.reply
(
    reply_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    post_id int(11) NOT NULL,
    user_id int(11) NOT NULL,
    floor int(11) NOT NULL,
    reply_content varchar(255) NOT NULL,
    reply_time datetime DEFAULT CURRENT_TIMESTAMP,
    reply_who int(11) NOT NULL COMMENT 'is a reply id'
);
CREATE TABLE forum.`right`
(
    user_id int(11) PRIMARY KEY NOT NULL,
    level int(11) NOT NULL
);
CREATE TABLE forum.user
(
    user_id int(11) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username varchar(64) NOT NULL,
    email varchar(64) NOT NULL,
    nickname varchar(64) DEFAULT '',
    signature varchar(255) DEFAULT 'I love IT',
    image varchar(64) DEFAULT '',
    xp int(11) DEFAULT 0,
    hash varchar(255) NOT NULL,
    salt varchar(255) NOT NULL,
    gender char(2) DEFAULT '',
    age int(11) DEFAULT 0
);
