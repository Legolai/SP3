CREATE
DATABASE IF NOT EXISTS Tournament_program_DB DEFAULT CHARSET = utf8mb4;
USE
Tournament_program_DB;
SET
FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Tournament;
DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS Tournament_team;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Match_game;
DROP TABLE IF EXISTS Sport;
DROP TABLE IF EXISTS Rule;
SET
FOREIGN_KEY_CHECKS = 1;


CREATE TABLE Sport
(
    sport_id   TINYINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sport_name VARCHAR(255) NOT NULL
);

CREATE TABLE Tournament
(
    tournament_id   TINYINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tournament_name VARCHAR(255) NOT NULL UNIQUE,
    sport_id        TINYINT      NOT NULL,
    FOREIGN KEY (sport_id) REFERENCES Sport (sport_id)
);

CREATE TABLE Team
(
    team_id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Tournament_team
(
    tournament_id TINYINT NOT NULL,
    team_id       INT     NOT NULL,
    point         TINYINT NOT NULL DEFAULT 0,
    score         TINYINT NOT NULL DEFAULT 0,
    FOREIGN KEY (tournament_id) REFERENCES Tournament (tournament_id),
    FOREIGN KEY (team_id) REFERENCES Team (team_id),
    CONSTRAINT tournament_team_id PRIMARY KEY (tournament_id, team_id)
);

CREATE TABLE Player
(
    player_id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL,
    team_id     INT          NOT NULL,
    FOREIGN KEY (team_id) REFERENCES Team (team_id)
);

CREATE TABLE Match_game
(
    match_game_id          INT          NOT NULL AUTO_INCREMENT,
    match_game_type        ENUM("SCORE", "TIME"),
    match_game_home_score  TINYINT UNSIGNED,
    match_game_guest_score TINYINT UNSIGNED,
    home_team_id           INT,
    guest_team_id          INT,
    tournament_id          TINYINT      NOT NULL,
    tournament_name        VARCHAR(255) NOT NULL,
    match_game_date        DATETIME     NOT NULL,
    match_game_is_done     BOOLEAN      NOT NULL,
    FOREIGN KEY (home_team_id) REFERENCES Team (team_id),
    FOREIGN KEY (guest_team_id) REFERENCES Team (team_id),
    FOREIGN KEY (tournament_id) REFERENCES Tournament (tournament_id),
    FOREIGN KEY (tournament_name) REFERENCES Tournament (tournament_name),
    CONSTRAINT PK_match_ID PRIMARY KEY (match_game_id, tournament_name),
    CHECK (home_team_id <> guest_team_id)
);

CREATE TABLE Rule
(
    rule_id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rule_name VARCHAR(255) NOT NULL,
    rule_text TEXT         NOT NULL,
    sport_id  TINYINT      NOT NULL,
    FOREIGN KEY (sport_id) REFERENCES Sport (sport_id)
);

INSERT INTO Sport (sport_name)
VALUES ("Bordfodbold");
INSERT INTO Sport (sport_name)
VALUES ("Beerpong");

INSERT INTO Team (team_name)
VALUES ("De hurtige knægte");
INSERT INTO Team (team_name)
VALUES ("De farlige sørøver");
INSERT INTO Team (team_name)
VALUES ("Guldgraverne");
INSERT INTO Team (team_name)
VALUES ("De vilde hunde");
INSERT INTO Team (team_name)
VALUES ("BetaHouse");
INSERT INTO Team (team_name)
VALUES ("Stjernerne");
INSERT INTO Team (team_name)
VALUES ("Dreamteam");
INSERT INTO Team (team_name)
VALUES ("Lærerne");
INSERT INTO Team (team_name)
VALUES ("Hestepigerne");
INSERT INTO Team (team_name)
VALUES ("Prisjægerne");
INSERT INTO Team (team_name)
VALUES ("Unidad");
INSERT INTO Team (team_name)
VALUES ("Zoomerne");
INSERT INTO Team (team_name)
VALUES ("Boomerne");
INSERT INTO Team (team_name)
VALUES ("De løse håndled");
INSERT INTO Team (team_name)
VALUES ("Spinners");
INSERT INTO Team (team_name)
VALUES ("Lektiehjælperne");


INSERT INTO Player (player_name, team_id)
VALUES ("Thomas", 1);
INSERT INTO Player (player_name, team_id)
VALUES ("Michael", 1);
INSERT INTO Player (player_name, team_id)
VALUES ("Freya", 2);
INSERT INTO Player (player_name, team_id)
VALUES ("Henrik", 2);
INSERT INTO Player (player_name, team_id)
VALUES ("Nicolai", 3);
INSERT INTO Player (player_name, team_id)
VALUES ("Nikolaj", 3);
INSERT INTO Player (player_name, team_id)
VALUES ("Morten", 4);
INSERT INTO Player (player_name, team_id)
VALUES ("Torben", 4);
INSERT INTO Player (player_name, team_id)
VALUES ("Bob", 5);
INSERT INTO Player (player_name, team_id)
VALUES ("Ib", 5);
INSERT INTO Player (player_name, team_id)
VALUES ("Lukas", 6);
INSERT INTO Player (player_name, team_id)
VALUES ("Troels", 6);
INSERT INTO Player (player_name, team_id)
VALUES ("Anna", 7);
INSERT INTO Player (player_name, team_id)
VALUES ("Lis", 7);
INSERT INTO Player (player_name, team_id)
VALUES ("Jasmin", 8);
INSERT INTO Player (player_name, team_id)
VALUES ("Lotte", 8);
INSERT INTO Player (player_name, team_id)
VALUES ("Kaj", 9);
INSERT INTO Player (player_name, team_id)
VALUES ("Andrea", 9);
INSERT INTO Player (player_name, team_id)
VALUES ("Jesper", 10);
INSERT INTO Player (player_name, team_id)
VALUES ("Simon", 10);
INSERT INTO Player (player_name, team_id)
VALUES ("Oscar", 11);
INSERT INTO Player (player_name, team_id)
VALUES ("Gorm", 11);
INSERT INTO Player (player_name, team_id)
VALUES ("Jonathan", 12);
INSERT INTO Player (player_name, team_id)
VALUES ("Lars", 12);
INSERT INTO Player (player_name, team_id)
VALUES ("Sofia", 13);
INSERT INTO Player (player_name, team_id)
VALUES ("Søren", 13);
INSERT INTO Player (player_name, team_id)
VALUES ("Rasmus", 14);
INSERT INTO Player (player_name, team_id)
VALUES ("Mark", 14);
INSERT INTO Player (player_name, team_id)
VALUES ("Viktor", 15);
INSERT INTO Player (player_name, team_id)
VALUES ("Christoffer", 15);
INSERT INTO Player (player_name, team_id)
VALUES ("Obama", 16);
INSERT INTO Player (player_name, team_id)
VALUES ("Kim", 16);

INSERT INTO Tournament (tournament_name, sport_id)
values ("Bordfodboldstunering 2021", 1);
INSERT INTO Tournament (tournament_name, sport_id)
values ("Bordfodboldstunering 2022", 1);

INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 1);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 2);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 3);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 4);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 5);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 6);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 7);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 8);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 9);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 10);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 11);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 12);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 13);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 14);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 15);
INSERT INTO Tournament_team (tournament_id, team_id)
values (1, 16);

INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 1);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 2);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 3);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 4);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 5);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 6);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 7);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 8);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 9);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 10);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 11);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 12);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 13);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 14);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 15);
INSERT INTO Tournament_team (tournament_id, team_id)
values (2, 16);
