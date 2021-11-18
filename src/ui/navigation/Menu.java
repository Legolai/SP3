package ui.navigation;

import ui.UI;

public abstract class Menu {
    protected final String name;
    protected final UI ui;
    private final String[] msgOptions;
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

    private void showHeader() {
        if (isHeaderShown)
            ui.println("---| " + name + " |---");
    }

    protected void showCustomHeader(String customHeader) {
        ui.println("---| " + customHeader + " |---");
    }

    private void showOptions(String lastOptionKey) {
        if (msgOptions.length != 0) {
            ui.printOptions(msgOptions, lastOptionKey);
        }
    }

    public abstract void show(Navigator navigation);

    protected void showMenu(String lastOptionKey) {
        showHeader();
        showOptions(lastOptionKey);
    }

    protected int getNumberOfOptions() {
        return msgOptions.length;
    }

    public String getName() {
        return name;
    }
}
