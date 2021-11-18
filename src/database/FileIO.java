package database;

import domain.match.Match;
import domain.team.Player;
import domain.team.Team;
import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;
import domain.tournament.TournamentTeam;
import ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class FileIO implements IO {

    private final UI ui;

    public FileIO() {
        this.ui = new UI();
    }

    @Override
    public HashMap<String, Tournament> loadTournaments() {
        HashMap<String, Tournament> tournaments = new HashMap<>();
        File tournamentFile = new File("src/resources/tournaments.txt");
        File tournamentTeamFile = new File("src/resources/tournamentTeams.txt");
        File matchFile = new File("src/resources/matchs.txt");
        try {
            Scanner scTournament = new Scanner(tournamentFile);

            ArrayList<Team> teams = new ArrayList<>(loadTeams().values());
            teams.sort(Comparator.comparingInt(Team::getID));
            // skip header
            scTournament.nextLine();


            while (scTournament.hasNext()) {
                String[] tournamentData = scTournament.nextLine().split(",");
                int tournamentID = Integer.parseInt(tournamentData[0].trim());
                String tournamentName = tournamentData[1].trim();
                String sportName = tournamentData[2].trim();

                Scanner scTournamentTeam = new Scanner(tournamentTeamFile);
                scTournamentTeam.nextLine();

                ArrayList<TournamentTeam> tournamentTeams = new ArrayList<>();

                while (scTournamentTeam.hasNext()) {
                    String[] tournamentTeamData = scTournamentTeam.nextLine().split(",");
                    int tournamentConnectID = Integer.parseInt(tournamentTeamData[0].trim());
                    if (tournamentConnectID != tournamentID) {
                        continue;
                    }
                    int teamID = Integer.parseInt(tournamentTeamData[1].trim());
                    int point = Integer.parseInt(tournamentTeamData[2].trim());
                    int score = Integer.parseInt(tournamentTeamData[3].trim());

                    tournamentTeams.add(new TournamentTeam(teams.get(teamID - 1), point, score));
                }

                tournamentTeams.sort(Comparator.comparingInt(a -> a.getTeam().getID()));
                Scanner scMatch = new Scanner(matchFile);
                scMatch.nextLine();

                Match match;
                ArrayList<Match> matches = new ArrayList<>();
                while (scMatch.hasNext()) {
                    String[] matchData = scMatch.nextLine().split(",");
                    int tournamentConnectID = Integer.parseInt(matchData[6].trim());
                    if (tournamentConnectID != tournamentID) {
                        continue;
                    }
                    int matchCount = Integer.parseInt(matchData[0].trim());
                    int homeTeamScore = Integer.parseInt(matchData[2].trim());
                    int guestTeamScore = Integer.parseInt(matchData[3].trim());
                    Integer homeTeam = Integer.getInteger(matchData[4].trim());
                    Integer guestTeam = Integer.getInteger(matchData[5].trim());
                    boolean matchIsDone = Boolean.parseBoolean(matchData[matchData.length - 1].trim());
                    LocalDateTime date = LocalDateTime.parse(matchData[matchData.length - 2].trim());

                    match = new Match(matchCount, tournamentName, homeTeam != null ? tournamentTeams.get(homeTeam - 1) : null, homeTeam != null ? tournamentTeams.get(guestTeam - 1) : null, date);
                    if (matchIsDone) {
                        match.setResult(homeTeamScore, guestTeamScore, false);
                    }
                    matches.add(match);
                }


                if (tournamentTeams.isEmpty() && matches.isEmpty()) {
                    tournaments.put(tournamentName.toLowerCase(), new KnockOutTournament(tournamentID, tournamentName, new Sport(sportName)));
                } else if (!tournamentTeams.isEmpty() && matches.isEmpty()) {
                    tournaments.put(tournamentName.toLowerCase(), new KnockOutTournament(tournamentID, tournamentName, new Sport(sportName), tournamentTeams));
                } else {
                    tournaments.put(tournamentName.toLowerCase(), new KnockOutTournament(tournamentID, tournamentName, new Sport(sportName), tournamentTeams, matches));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    @Override
    public HashMap<String, Team> loadTeams() {
        HashMap<String, Team> teams = new HashMap<>();
        try {
            File teamFile = new File("src/resources/teams.txt");
            File playerFile = new File("src/resources/players.txt");
            Scanner scTeam = new Scanner(teamFile);

            // skip header
            scTeam.nextLine();

            while (scTeam.hasNext()) {
                String[] teamData = scTeam.nextLine().split(",");
                int teamID = Integer.parseInt(teamData[0].trim());
                String teamName = teamData[1].trim();
                ArrayList<Player> players = new ArrayList<>();

                Scanner scPlayer = new Scanner(playerFile);
                scPlayer.nextLine();

                while (scPlayer.hasNext()) {
                    String[] playerData = scPlayer.nextLine().split(",");
                    int teamConnectID = Integer.parseInt(playerData[2].trim());
                    if (teamConnectID != teamID) {
                        continue;
                    }
                    int playerID = Integer.parseInt(playerData[0].trim());
                    String playerName = playerData[1].trim();

                    players.add(new Player(playerID, playerName));
                }

                teams.put(teamName.toLowerCase(), new Team(teamID, teamName, players));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public void saveTournaments(HashMap<String, Tournament> tournaments) {
        File tournamentFile = new File("src/resources/tournaments.txt");
        File tournamentTeamFile = new File("src/resources/tournamentTeams.txt");
        File matchFile = new File("src/resources/matchs.txt");

        try {
            ui.println("Saving tournament data...");
            if (tournamentFile.createNewFile())
                ui.println("The tournament file has been created");
            if (tournamentTeamFile.createNewFile())
                ui.println("The tournament team file has been created");
            if (matchFile.createNewFile())
                ui.println("The match file has been created");

            FileWriter tournamentWriter = new FileWriter(tournamentFile);
            FileWriter tournamentTeamWriter = new FileWriter(tournamentTeamFile);
            FileWriter matchWriter = new FileWriter(matchFile);

            StringBuilder tournamentData = new StringBuilder("ID, Name, sport_name\n");
            StringBuilder tournamentTeamData = new StringBuilder("tournament_id, team_id, point, score\n");
            StringBuilder matchData = new StringBuilder("match_game_id, match_game_type, match_game_home_score, match_game_guest_score, home_team_id, guest_team_id, tournament_id, tournament_name, match_game_date, match_game_is_done\n");


            ArrayList<Tournament> listTournament = new ArrayList<>(tournaments.values());
            listTournament.sort(Comparator.comparingInt(Tournament::getID));

            for (Tournament tn : listTournament) {
                tournamentData.append(tn.getID()).append(", ").append(tn.getName()).append(", ").append(tn.getSport().getSPORTNAME()).append("\n");
                tn.getContenders().sort(Comparator.comparingInt(a -> a.getTeam().getID()));
                for (TournamentTeam tnTeam : tn.getContenders()) {
                    tournamentTeamData.append(tn.getID()).append(", ").append(tnTeam.getTeam().getID()).append(", ").append(tnTeam.getPoint()).append(", ").append(tnTeam.getScore()).append("\n");
                }
                for (Match m : tn.getMatchProgram().getAllMatches()) {
                    matchData.append(m.getID().split("_")[0]).append(", ").append("SCORE").append(", ")
                            .append(m.getScore()[0]).append(", ").append(m.getScore()[1]).append(", ").append(m.getTeam(0) != null ? m.getTeam(0).getTeam().getID() : null)
                            .append(", ").append(m.getTeam(1) != null ? m.getTeam(1).getTeam().getID() : null).append(", ").append(tn.getID()).append(", ")
                            .append(tn.getName()).append(", ").append(m.getDate()).append(", ").append(m.getWinner() != null).append("\n");
                }

            }

            tournamentWriter.write(tournamentData.toString());
            tournamentTeamWriter.write(tournamentTeamData.toString());
            matchWriter.write(matchData.toString());
            tournamentWriter.close();
            tournamentTeamWriter.close();
            matchWriter.close();
            ui.println("Tournament data has been saved...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTeams(HashMap<String, Team> teams) {
        File teamFile = new File("src/resources/teams.txt");
        File playerFile = new File("src/resources/players.txt");
        try {
            ui.println("Saving team data...");
            if (teamFile.createNewFile())
                ui.println("The team file has been created");
            if (playerFile.createNewFile())
                ui.println("The player file has been created");

            FileWriter teamWriter = new FileWriter(teamFile);
            FileWriter playerWriter = new FileWriter(playerFile);

            StringBuilder teamData = new StringBuilder("team_id, team_name\n");
            StringBuilder playerData = new StringBuilder("player_id, player_name, team_id\n");

            ArrayList<Team> listTeams = new ArrayList<>(teams.values());
            listTeams.sort(Comparator.comparingInt(Team::getID));

            for (Team tm : listTeams) {
                teamData.append(tm.getID()).append(", ").append(tm.getName()).append("\n");
                for (Player p : tm.getTeamMembers()) {
                    playerData.append(p.getID()).append(", ").append(p.getName()).append(", ").append(tm.getID()).append("\n");
                }
            }

            teamWriter.write(teamData.toString());
            playerWriter.write(playerData.toString());
            teamWriter.close();
            playerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
