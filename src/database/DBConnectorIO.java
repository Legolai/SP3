package database;

import domain.match.Match;
import domain.team.Player;
import domain.team.Team;
import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;
import domain.tournament.TournamentTeam;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

public class DBConnectorIO implements IO {

    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/Tournament_program_DB";

    //  Database credentials
    static final String USER = "tuneringsFormand";
    static final String PASS = "GamleGorm#12";


    @Override
    public HashMap<String, Tournament> loadTournaments() {
        HashMap<String, Tournament> tournaments = new HashMap<>();
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        try{
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            String sql = "SELECT Tournament.tournament_id, Tournament.tournament_name, Sport.sport_name\n" +
                    "FROM Tournament\n" +
                    "INNER JOIN Sport ON Tournament.sport_id=Sport.sport_id\n" +
                    "ORDER BY Tournament.tournament_id ASC;";
            ResultSet rs = stmt.executeQuery(sql);

            HashMap<String, Team> teams = loadTeams();
            //STEP 5: Extract data from result set
            while(rs.next()){
                // Create Tournament

                int tournamentID = rs.getInt("tournament_id");
                String tournamentName = rs.getString("tournament_name");
                String sportName = rs.getString("sport_name");
                ArrayList<TournamentTeam> tournamentTeams = new ArrayList<>();

                sql = "SELECT Team.team_name, Tournament_team.point, Tournament_team.score\n" +
                        "FROM Tournament_team\n" +
                        "INNER JOIN Team ON Tournament_team.team_id=Team.team_id\n" +
                        "WHERE Tournament_team.tournament_id="+ tournamentID+"\n" +
                        "ORDER BY Tournament_team.team_id ASC;";
                ResultSet rsTournamentTeams = stmt2.executeQuery(sql);

                while (rsTournamentTeams.next()){
                    String teamName = rsTournamentTeams.getString("team_name");
                    int point = rsTournamentTeams.getInt("point");
                    int score = rsTournamentTeams.getInt("score");

                    tournamentTeams.add(new TournamentTeam(teams.get(teamName.toLowerCase()),point, score));
                }
                rsTournamentTeams.close();
                tournaments.put(tournamentName.toLowerCase(), new KnockOutTournament(tournamentID, tournamentName, new Sport(sportName), tournamentTeams));
                tournamentTeams = new ArrayList<>();
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            stmt2.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
                if(stmt2!=null)
                    stmt2.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try


        return tournaments;
    }

    @Override
    public HashMap<String, Team> loadTeams() {
        HashMap<String, Team> teams = new HashMap<>();
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        try{
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();


            String sql = "SELECT * FROM Team ORDER BY Team.team_id ASC;";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name

                int teamID = rs.getInt("team_id");
                String teamName = rs.getString("team_name");

                sql = "SELECT player_name FROM Player WHERE team_id="+ teamID +";";
                ResultSet rsPlayers = stmt2.executeQuery(sql);
                ArrayList<Player> players = new ArrayList<>();
                while (rsPlayers.next()){
                    players.add(new Player(rsPlayers.getString("player_name")));
                }
                rsPlayers.close();
                teams.put(teamName.toLowerCase(), new Team(teamID, teamName, players));
                players = new ArrayList<>();
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            stmt2.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
                if(stmt2!=null)
                    stmt2.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try


        return teams;
    }

    @Override
    public void saveTournaments(HashMap<String, Tournament> tournaments) {

        Connection conn;
        String sql = "INSERT INTO Tournament_team(tournament_id, team_id, point, score) " +
                "VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE point=?, score=?;";
        try {
            conn = DriverManager.getConnection(DB_URL,USER, PASS);
            System.out.println("Creating statement...");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            System.out.println("Creating statement...");
            sql = "INSERT INTO Match_game(match_game_id, match_game_type, match_game_home_score, match_game_guest_score, home_team_id, guest_team_id, tournament_id, tournament_name, match_game_date, match_game_is_done) \n" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE match_game_home_score=?, match_game_guest_score=?, home_team_id=?, guest_team_id=?, match_game_is_done=?;";
            PreparedStatement pstmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Tournament tn: tournaments.values()) {
                for (TournamentTeam team: tn.getContenders()) {
                    pstmt.setInt(1, tn.getID());
                    pstmt.setInt(2, team.getTeam().getID());
                    pstmt.setInt(3, team.getPoint());
                    pstmt.setInt(4, team.getScore());

                    // These parameters are used if UPDATES are identic with parameter 3, 4, 5

                    pstmt.setInt(5, team.getPoint());
                    pstmt.setInt(6, team.getScore());

                    pstmt.addBatch();
                }

                for (Match match: tn.getMatchProgram().getAllMatches()) {
                    pstmt2.setInt(1, Integer.parseInt(match.getID().split("_")[0]));
                    pstmt2.setString(2, match.getType());
                    pstmt2.setInt(3, match.getScore()[0]);
                    pstmt2.setInt(4, match.getScore()[1]);
                    pstmt2.setObject(5, (match.getTeam(0) != null ? match.getTeam(0).getTeam().getID() : null));
                    pstmt2.setObject(6, (match.getTeam(1) != null ? match.getTeam(1).getTeam().getID() : null));
                    pstmt2.setInt(7, tn.getID());
                    pstmt2.setString(8, tn.getName());
                    pstmt2.setObject(9, LocalDateTime.ofInstant(match.getDate(), ZoneId.of("Europe/Copenhagen")));
                    pstmt2.setBoolean(10, match.getWinner() != null);

                    pstmt2.setInt(11, match.getScore()[0]);
                    pstmt2.setInt(12, match.getScore()[1]);
                    pstmt2.setObject(13, (match.getTeam(0) != null ? match.getTeam(0).getTeam().getID() : null));
                    pstmt2.setObject(14, (match.getTeam(1) != null ? match.getTeam(1).getTeam().getID() : null));
                    pstmt2.setBoolean(15, match.getWinner() != null);

                    pstmt2.addBatch();
                }
            }
            pstmt.executeBatch();
            pstmt2.executeBatch();
            pstmt.close();
            pstmt2.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void saveTeams(HashMap<String, Team> teams) {

    }
}