CREATE TABLE reviews (
    review_id INT AUTO_INCREMENT,
    user_id VARCHAR(20),
    content_id INT,
    review_title VARCHAR(100) NOT NULL,
    review_content TEXT NOT NULL,
    review_img VARCHAR(1000),
    review_regtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (review_id, user_id, content_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (content_id) REFERENCES attraction_info(content_id) ON DELETE CASCADE ON UPDATE CASCADE
);