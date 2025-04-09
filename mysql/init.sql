CREATE DATABASE IF NOT EXISTS dev_my_diary;

USE dev_my_diary;

CREATE TABLE IF NOT EXISTS users(
id INT AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(10) NOT NULL,
password VARCHAR(10),
join_date DATE
);

CREATE TABLE IF NOT EXISTS diaries(
id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT,
post_date DATETIME,
comment VARCHAR(500),
picture BLOB,
FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (user_name, password, join_date) VALUES
('JohnDoe', 'password1', '2025-04-01'),
('JaneDoe', 'password2', '2025-04-02');

INSERT INTO diaries (user_id, post_date, comment) VALUES
(1, '2025-04-05 10:00:00', 'This is a test diary entry for John Doe.');