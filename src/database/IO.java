package database;

import domain.team.Team;
import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;
import ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        Tournament tournament;
        Sport sport;
        Team team = null;
        while (sc.hasNext()){
            String[] lineData = sc.nextLine().split(",");
            sport = new Sport(lineData[1]);
            tournament = new KnockOutTournament(lineData[0], sport);
            tournaments.put(tournament.getName().toLowerCase(), tournament);
        }
        return tournaments;
    }
}
