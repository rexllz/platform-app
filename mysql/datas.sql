INSERT INTO forum.post (post_id, title, like_num, module, label, user_id, post_time, file_paths, post_content, update_time, reply_num) VALUES (1, '66666', 11, 'module', 'label', 3, '2018-10-28 18:02:26', 'path', 'good', '2018-10-28 23:21:57', 1);
INSERT INTO forum.reply (reply_id, post_id, user_id, floor, reply_content, reply_time, reply_who) VALUES (1, 2, 2, 2, '�Ҳ�����', '2018-10-28 13:45:30', 5);
INSERT INTO forum.reply (reply_id, post_id, user_id, floor, reply_content, reply_time, reply_who) VALUES (3, 3, 4, 4, 'testmore', '2018-10-28 13:59:00', 4);
INSERT INTO forum.reply (reply_id, post_id, user_id, floor, reply_content, reply_time, reply_who) VALUES (5, 2, 2, 1, 'hahahh6622', '2018-10-28 14:15:11', 2);
INSERT INTO forum.reply (reply_id, post_id, user_id, floor, reply_content, reply_time, reply_who) VALUES (8, 2, 2, 1, 'stange', '2018-10-28 15:06:20', 2);
INSERT INTO forum.user (user_id, username, email, nickname, signature, image, xp, hash, salt, gender, age) VALUES (1, 'rex', 'email', 'dragon', 'this is a test', 'jpg', 666, 'hash', 'salt', '01', 10);
INSERT INTO forum.user (user_id, username, email, nickname, signature, image, xp, hash, salt, gender, age) VALUES (2, 'fan', 'gmaill', 'ff', 'I love IT', 'bmp', 0, 'hash', 'salt', '02', 11);
INSERT INTO forum.user (user_id, username, email, nickname, signature, image, xp, hash, salt, gender, age) VALUES (3, 'fan', 'qqmail', 'nick', 'loveu', 'jpg', 0, 'hash', 'salt', '02', 11);
INSERT INTO forum.user (user_id, username, email, nickname, signature, image, xp, hash, salt, gender, age) VALUES (4, 'fanz', '163mail', 'nick', 'loveu', 'jpg', 0, 'hash', 'salt', '02', 11);