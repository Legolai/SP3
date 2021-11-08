package ui.navigation;

import ui.Program;

import java.util.HashMap;

public class QuitMenu extends Menu {
    private final Program app;

    public QuitMenu(String name, boolean isHeaderShown ,Program app) {
        super(name, isHeaderShown);
        this.app = app;
    }

    @Override
    public void show(Navigator navigation) {
        super.showMenu( "");
        switch ((ui.getUserOption("Are you sure you want to quit? (y/n)", new String[]{"y", "n"}))) {
            case "y" -> app.quit();
            case "n" -> navigation.goTo("MainMenu");
        }
    }
}
