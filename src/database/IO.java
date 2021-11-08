package database;

import domain.team.Player;
import domain.team.Team;
import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;
import ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class IO {
    private UI ui;

    public IO() {
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

    public void saveTournamentsToFile(String path, HashMap<String, Tournament> tournaments) throws IOException {
        File file = new File(path);
        if (file.createNewFile())
            ui.println("The file has been created");
        FileWriter writer = new FileWriter(path);
        StringBuilder data = new StringBuilder();
        for (String key : tournaments.keySet()) {
            Tournament tn = tournaments.get(key);
            data.append(tn.getName()).append(",").append("Fodbold,").append("\n");
        }
        writer.write(data.toString());
        writer.close();
    }

    public HashMap<String, Tournament> loadTournaments(String path) throws FileNotFoundException {
        HashMap<String, Tournament> tournaments = new HashMap<>();
        File file = new File(path);
        Scanner sc = new Scanner(file);
        ArrayList<Team> teams = new ArrayList<>( loadTeams("src/resources/teams.txt").values());
        Tournament tournament;
        Sport sport;
        while (sc.hasNext()){
            String[] lineData = sc.nextLine().split(",");
            sport = new Sport(lineData[1]);
            tournament = new KnockOutTournament(lineData[0], sport, teams);
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
        while (sc.hasNext()){
            String[] lineData = sc.nextLine().split(",");
            for (int i = 1; i < lineData.length; i++) {
                players.add(new Player(lineData[i]));
            }
            team = new Team(lineData[0],players);
            teams.put(team.getName().toLowerCase(),team);
        }
        return teams;
    }
}
