CREATE TABLE social_media.friendships
(
    id       BIGINT auto_increment NOT NULL,
    user_id  BIGINT NOT NULL,
    other_id BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    CONSTRAINT friendships_PK PRIMARY KEY (id),
    CONSTRAINT friendships_FK FOREIGN KEY (user_id) REFERENCES social_media.users (user_id)
)