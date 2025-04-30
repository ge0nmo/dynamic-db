CREATE TABLE Team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    db_url VARCHAR(255) NOT NULL,
    db_username VARCHAR(50) NOT NULL,
    db_password VARCHAR(50) NOT NULL
);

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    age INT,
    name VARCHAR(50),
    member_code VARCHAR(50),
    team_id BIGINT NOT NULL,
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES Team(id)
);


INSERT INTO Team (name, db_url, db_username, db_password) VALUES
('Real Madrid', 'jdbc:mysql://real-madrid:3306/real_madrid', 'root', 'root'),
('Manchester City', 'jdbc:mysql://manchester-city:3306/manchester_city', 'root', 'root');

INSERT INTO member (age, name, member_code, team_id) VALUES
(39, 'Luka Modric', 'RM1001', 1),
(26, 'Kylian Mbappe', 'RM1002', 1),
(33, 'Kevin De Bruyne', 'MC2001', 2),
(24, 'Erling Haaland', 'MC2002', 2);