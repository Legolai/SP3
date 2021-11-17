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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class IOtingting {
    private UI ui;

    public IOtingting() {
        ui = new UI();
    }


    public void readFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner data = new Scanner(file);
    }

    public void writeToFile(String path, String s) throws IOException {
        File file = new File(path);
        if (file.createNewFile())
            ui.println("The has been created");
        FileWriter writer = new FileWriter(path);
        writer.write(s);
        writer.close();
    }

    public void saveTournamentsToDir(String path, HashMap<String, Tournament> tournaments) throws IOException {
        for (String key : tournaments.keySet()) {
            saveTournamentToFile(path, tournaments.get(key));
        }
    }

    public HashMap<String, Tournament> loadTournaments(String path) throws FileNotFoundException {
        HashMap<String, Tournament> tournaments = new HashMap<>();
        File file = new File(path);
        Scanner sc = new Scanner(file);
        ArrayList<Team> tms = new ArrayList<>(loadTeams("src/resources/teams.txt").values());
        ArrayList<TournamentTeam> teams = new ArrayList<>();
        for (Team tm : tms) {
            teams.add(new TournamentTeam(tm));
        }

        Tournament tournament;
        Sport sport;
        while (sc.hasNext()) {
            String[] lineData = sc.nextLine().split(",");
            sport = new Sport(lineData[1]);
            tournament = new KnockOutTournament(lineData[0], sport, teams);
            tournament.createMatchProgram("score");
            tournaments.put(tournament.getName().toLowerCase(), tournament);
        }
        return tournaments;
    }


    public HashMap<String, Team> loadTeams(String path) throws FileNotFoundException {
        HashMap<String, Team> teams = new HashMap<>();
        File file = new File(path);
        Scanner sc = new Scanner(file);
        ArrayList<Player> players = new ArrayList<>();
        Team team;
        while (sc.hasNext()) {
            String[] lineData = sc.nextLine().split(",");
            for (int i = 1; i < lineData.length; i++) {
                players.add(new Player(lineData[i].replace(" ", "")));
            }
            team = new Team(lineData[0], players);
            teams.put(team.getName().toLowerCase(), team);
            players = new ArrayList<>();
        }
        return teams;
    }

    public void saveTeamsToFile(String path, HashMap<String, Team> teams) throws IOException {
        File file = new File(path);
        if (file.createNewFile())
            ui.println("The file has been created");
        FileWriter writer = new FileWriter(path);
        StringBuilder data = new StringBuilder();
        for (String key : teams.keySet()) {
            Team t = teams.get(key);
            data.append(t.getName()).append(", ");
            for (Player pl : t.getTeamMembers()) {
                data.append(pl.getName()).append(", ");
            }
            data.append("\n");
        }
        writer.write(data.toString());
        writer.close();
    }

    public void saveTournamentToFile(String path, Tournament tournament) throws IOException {
        String filePath = path + "/tournament_" + tournament.getName().replace(" ", "_")+".txt";
        File file = new File(filePath);
        if (file.createNewFile())
            ui.println("The file has been created");
        FileWriter writer = new FileWriter(filePath);
        StringBuilder data = new StringBuilder();
        data.append(tournament.getSport().getSPORTNAME()).append(", ");
        data.append(tournament.getName()).append("\n");
        data.append("List of all the matches:").append("\n");
        String[] fraction = {"Final", "Semifinals", "Quarterfinals", "8th-finals", "16th-finals", "32-finals"};
        int counter = 1;
        ArrayList<ArrayList<Match>> KnockoutBracket = tournament.getMatchProgram().getKnockoutBracket();
        data.append(KnockoutBracket.size()+"");
        for (int i = 0; i < KnockoutBracket.size(); i++) {
            data.append(fraction[KnockoutBracket.size() - 1 - i]).append("\n");
            for (int j = 0; j < KnockoutBracket.get(i).size(); j++) {
                Match match = KnockoutBracket.get(i).get(j);
                data.append("Match ").append(counter).append(". ").append(match
                        .shortToString()).append("\n");
                counter++;
            }
        }
        //for (Match m : tournament.getMatchProgram().getAllMatches()) {
        //    data.append("Match ").append(counter).append(". ").append(m.shortToString()).append("\n");
        //}
        writer.write(data.toString());
        writer.close();
    }

}


