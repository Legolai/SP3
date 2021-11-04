package ui.menus;

import domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Menu{
    ArrayList<Tournament> tournaments;

    public MainMenu(String name,ArrayList<Tournament> tournaments){
        super(name, new String[]{
                "View tournaments" + (tournaments.isEmpty() ? "(not available)" : ""),
                "Create tournament",
                "Quit Program"
        });
        this.tournaments = tournaments;
    }

    @Override
    public void show(HashMap<String, Menu> menus) {
        clearScreen();
        super.show(menus, "q");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(),"q")){
            case "1": menus.get("Tournaments").show(menus);
            case "2": menus.get("New Tournament").show(menus);
            default: menus.get("Quit").show(menus); break;
        }

    }
}
