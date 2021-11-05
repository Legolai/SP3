package ui.navigation;

import domain.match.Match;
import domain.team.Team;
import domain.tournament.Tournament;
import ui.UI;

import java.util.HashMap;

public abstract class Menu {
    private final String[] msgOptions;
    protected final String name;
    protected final UI ui;
    private final boolean isHeaderShown;

    public Menu(String name, boolean isHeaderShown) {
        this.name = name;
        this.msgOptions = new String[0];
        this.isHeaderShown = isHeaderShown;
        ui = new UI();
    }

    public Menu(String name, boolean isHeaderShown, String[] msgOptions) {
        this.name = name;
        this.msgOptions = msgOptions;
        this.isHeaderShown = isHeaderShown;
        ui = new UI();
    }

    public void clearScreen() {
        ui.clear();
    }

    private void updateMenus(HashMap<String, Menu> navigation) {
        if (navigation.get("currMenu") == null || !navigation.get("currMenu").equals(navigation.get(name))) {
            navigation.put("prevMenu", navigation.get("currMenu"));
            navigation.put("currMenu", navigation.get(name));
        }
    }

    private void showHeader() {
        if (isHeaderShown)
            ui.println("---| " + name + " |---");
    }

    protected void showCustomHeader(String customHeader) {
        ui.println("---| " + customHeader + " |---");
    }

    private void showOptions(String lastOptionKey){
        if (msgOptions.length != 0) {
            ui.printOptions(msgOptions, lastOptionKey);
        }
    }

    public abstract void show(HashMap<String, Menu> navigation);

    protected void show(HashMap<String, Menu> navigation, String lastOptionKey) {
        showHeader();
        updateMenus(navigation);
        showOptions(lastOptionKey);
    }

    protected int getNumberOfOptions() {
        return msgOptions.length;
    }
}
