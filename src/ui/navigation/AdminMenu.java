package ui.navigation;

public class AdminMenu extends Menu {

    public AdminMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View tournaments",
                "Create tournament",
                "View teams",
                "Create team",
                "Quit Program"
        });
    }

    @Override
    public void show(Navigator navigation) {
        clearScreen();
        super.showMenu("q");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "q")) {
            case "1" -> navigation.goTo("Tournaments");
            case "2" -> navigation.goTo("New Tournament");
            case "3" -> navigation.goTo("Teams");
            case "4" -> navigation.goTo("New Team");
            default -> navigation.goTo("Quit");
        }
    }
}
