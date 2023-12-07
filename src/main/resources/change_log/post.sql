CREATE TABLE social_media.posts (
                                   user_id BIGINT NOT NULL,
                                   content VARCHAR(255) NOT NULL,
                                   id BIGINT auto_increment NOT NULL,
                                   `timestamp` TIMESTAMP NOT NULL,
                                   CONSTRAINT post_PK PRIMARY KEY (id),
                                   CONSTRAINT post_FK FOREIGN KEY (user_id) REFERENCES social_media.users(user_id)
)