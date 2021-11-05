package match;

import domain.team.Player;
import domain.team.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TemporaryTeamImporter {
    public ArrayList<Team> teams;

    public TemporaryTeamImporter() {
        teams = new ArrayList<>();
    }

    public void readFile() {
        try {
            File myObj = new File("tests/teamList");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);

                ArrayList<String> splitted = new ArrayList<>(Arrays.asList(data.split(" ")));
                String teamname = splitted.get(0);
                splitted.remove(0);
                ArrayList<Player> players = new ArrayList<>();
                for(String s : splitted) {
                    players.add(new Player(s));
                }
                teams.add(new Team(teamname,players));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
