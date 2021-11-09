package ui.navigation;

public class StartMenu extends Menu{
    public StartMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "Log in (Admin)",
                "Register new Team",
                "Quit Program"
        });
    }

    @Override
    public void show(Navigator navigation) {
        clearScreen();
        super.showMenu( "q");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "q")) {
            case "1" -> logInAdmin(navigation);
            case "2" -> navigation.goTo("New team");
            default -> navigation.goTo("Quit");
        }
    }

    private void logInAdmin(Navigator navigation){
       String username = ui.getUserInput("Username:");
       String password = ui.getUserInput("Password:");

       if(username.equalsIgnoreCase("Admin") && password.equals("Admin")){
           navigation.goTo("Admin");
       }else {
           ui.println("Wrong username or password!");
           ui.waitForUser();
           show(navigation);
       }
    }
}
