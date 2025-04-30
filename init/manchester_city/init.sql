CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_code VARCHAR(50) NOT NULL
);

CREATE TABLE game_score (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    score INT,
    member_id BIGINT,
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES member(id)
);

INSERT INTO member (member_code) VALUES
('MC2001'),
('MC2002');

INSERT INTO game_score (score, member_id) VALUES
(98, 1),
(91, 2);