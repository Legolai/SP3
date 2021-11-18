USE Tournament_program_DB;
SELECT Tournament.tournament_id, Tournament.tournament_name, Sport.sport_name, Team.team_id, Team.team_name, Tournament_team.score, Tournament_team.point
FROM Tournament
INNER JOIN Sport ON Tournament.sport_id=Sport.sport_id
INNER JOIN Tournament_team ON Tournament.tournament_id=Tournament_team.tournament_id
INNER JOIN Team ON Tournament_team.team_id=Team.team_id
ORDER BY Tournament.tournament_id, Tournament_team.team_id ASC;

SELECT * FROM Team ORDER BY Team.team_id ASC;

SELECT player_name FROM Player WHERE team_id=1;


SELECT Team.team_name, Tournament_team.point, Tournament_team.score
FROM Tournament_team
INNER JOIN Team ON Tournament_team.team_id=Team.team_id
WHERE Tournament_team.tournament_id=1
ORDER BY Tournament_team.team_id ASC;

INSERT INTO Tournament_team(tournament_id, team_id, point, score) VALUES(1,1,10,2) ON DUPLICATE KEY UPDATE point=10, score=2;

SELECT * FROM Tournament_team WHERE tournament_id=1;



	match_game_id INT NOT NULL AUTO_INCREMENT,
    match_game_type ENUM("SCORE", "TIME"),
    match_game_home_score TINYINT UNSIGNED,
    match_game_guest_score TINYINT UNSIGNED,
    home_team_id INT,
    guest_team_id INT,
    tournament_id TINYINT NOT NULL,
    tournament_name VARCHAR(255) NOT NULL,
    match_game_date DATETIME NOT NULL,
	match_game_is_done BOOLEAN NOT NULL,
    FOREIGN KEY (home_team_id) REFERENCES Team(team_id),
	FOREIGN KEY (guest_team_id) REFERENCES Team(team_id),
	FOREIGN KEY (tournament_id) REFERENCES Tournament(tournament_id),
    FOREIGN KEY (tournament_name) REFERENCES Tournament(tournament_name),
    CONSTRAINT PK_match_ID PRIMARY KEY (match_game_id, tournament_name)

INSERT INTO Match_game(match_game_id, match_game_type, match_game_home_score, match_game_guest_score, home_team_id, guest_team_id, tournament_id, tournament_name, match_game_date, match_game_is_done) 
VALUES(1,"SCORE", 0,0,1,2,1, "Bordfodboldstunering 2021", '2021-10-17 14:30:00', false) ON DUPLICATE KEY UPDATE match_game_home_score=1, match_game_guest_score=10, home_team_id=1, guest_team_id=1, match_game_is_done=true;

SELECT * FROM Tournament_team WHERE tournament_id=1;
SELECT Mg.match_game_id, Mg.match_game_type, Mg.match_game_home_score, Mg.match_game_guest_score, T1.team_name AS 'home_team', T2.team_name AS 'guest_team', Mg.match_game_date, Mg.match_game_is_done  
FROM Match_game Mg
LEFT JOIN Team T1 on T1.team_id=Mg.home_team_id
LEFT JOIN Team T2 on T2.team_id=Mg.guest_team_id
WHERE Mg.tournament_id=1;


SELECT * FROM Match_game Mg WHERE Mg.tournament_id=3;

INSERT INTO Tournament (tournament_name, sport_id) values ("Bordfodboldstunering 2023", 1);
SELECT * FROM Tournament;


INSERT INTO Team (team_id, team_name) VALUES (18, "De farverige fisker") ON DUPLICATE KEY UPDATE team_name=;
INSERT INTO Player (player_id, player_name, team_id) VALUES (17, "Hans", 1) ON DUPLICATE KEY UPDATE team_id=2 DELETE FROM Player WHERE team_id IS NULL;
INSERT INTO Team (team_name) VALUES ("De langsome orme");
SELECT * FROM Team;
SELECT * FROM Player;

-- INSERT INTO Player(ID, player_name, balance, position, is_next) VALUES(1,"Egon",30000,1,true) ON DUPLICATE KEY UPDATE balance=30000, position=1, is_next=true;
-- INSERT INTO Player(ID, player_name, balance, position, is_next) VALUES(2,"Kjeld",30000,1,false) ON DUPLICATE KEY UPDATE balance=30000, position=1, is_next=false;
-- INSERT INTO Player(ID, player_name, balance, position, is_next) VALUES(3,"Benny",30000,1,false) ON DUPLICATE KEY UPDATE balance=30000, position=1, is_next=false;