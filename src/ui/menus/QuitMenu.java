package ui.menus;

import ui.Program;

import java.util.HashMap;

public class QuitMenu extends Menu {
    private final Program app;

    public QuitMenu(String name,Program app) {
        super(name);
        this.app = app;
    }

    @Override
    public void show(HashMap<String, Menu> menus) {
        super.show(menus);
        String[] options = {"y","n"};
        switch ((ui.getUserOption("Are you sure you want to quit? (y/n)", options))) {
            case "y" -> app.quit();
            case "n" -> menus.get("prevMenu").show(menus);
        }
    }
}
