package ui.menus;

import domain.tournament.*;
import java.util.HashMap;

public class NewTournamentMenu extends Menu{
    public NewTournamentMenu(String name) {
        super(name,new String[]{
                "Create new knockout tournament",
                "Create new group tournament",
                "Go back",
        });
    }

    @Override
    public void show(HashMap<String, Menu> menus) {
        clearScreen();
        Tournament tournament;
        Sport sport = new Sport("Fodbold");
        String name = ui.getUserInput("Type the tournaments name: ").toLowerCase();

        super.show(menus, "b");
        String[] options = {"1","2","b"};
        switch ((ui.getUserOption("Select menu: " , options))){
            case "1": tournament = new KnockOutTournament(name, sport); break;
            case "2": break;
            default: menus.get("prevMenu").show(menus); break;
        }
    }
}
