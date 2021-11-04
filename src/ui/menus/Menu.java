package ui.menus;

import ui.UI;

import java.util.HashMap;

public abstract class Menu {
    private final String[] msgOptions;
    protected final String name;
    protected final UI ui;

    public Menu(String name){
        this.name = name;
        msgOptions = new String[0];
        ui = new UI();
    }

    public Menu(String name, String[] msgOptions){
        this.name = name;
        this.msgOptions = msgOptions;
        ui = new UI();
    }

    public void clearScreen(){
        ui.clear();
    }

    private void updateMenus(HashMap<String, Menu> menus){
        menus.put("prevMenu", menus.get("currMenu"));
        menus.put("currMenu", menus.get(name));
    }

    public void show(HashMap<String, Menu> menus){
        updateMenus(menus);
        if(msgOptions.length != 0)
            ui.printOptions(msgOptions, "");
    }

    public void show(HashMap<String, Menu> menus, String lastOptionKey){
        updateMenus(menus);
        if(msgOptions.length != 0)
            ui.printOptions(msgOptions, lastOptionKey.toUpperCase());
    }

    protected int getNumberOfOptions(){
        return msgOptions.length;
    }
}
