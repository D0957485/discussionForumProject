CREATE TABLE users
(
    user_id   INT UNSIGNED PRIMARY KEY auto_increment,
    user_name VARCHAR(100)           NOT NULL,
    role      ENUM ('user', 'admin') NOT NULL
);

CREATE TABLE users
(
	user_id  INT UNSIGNED NOT NULL,
	email    VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (user_id, email)
);

CREATE TABLE article
(
	article_id  INT UNSIGNED PRIMARY KEY auto_increment,
	content  VARCHAR(1000) NOT NULL,
	time     DATE          NOT NULL,
	user_id  INT UNSIGNED  NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
);

CREATE TABLE comment
(
    comment_id INT UNSIGNED PRIMARY KEY auto_increment,
    content    VARCHAR(1000) NOT NULL,
    time       DATE          NOT NULL,
    user_id    INT UNSIGNED  NOT NULL,
    article_id    INT UNSIGNED  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (article_id) REFERENCES article (article_id) ON UPDATE CASCADE ON DELETE CASCADE
);
