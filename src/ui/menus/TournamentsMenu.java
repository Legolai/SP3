package ui.menus;

import java.util.HashMap;

public class TournamentsMenu extends Menu{

    public TournamentsMenu(String name){
        super("name",new String[]{
            "View all tournaments",
            "Select tournament",
            "Go back",
        });
    }

    @Override
    public void show(HashMap<String, Menu> menus){
        clearScreen();
        super.show(menus, "b");
        switch (ui.getUserOption("Select menu: " , getNumberOfOptions(), "b")){
            case "1": break;
            case "2": ui.getUserInput("Type the name of tournament: "); break;
            default: menus.get("prevMenu").show(menus); break;
        }
    }

}
