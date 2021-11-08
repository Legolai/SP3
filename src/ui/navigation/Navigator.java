package ui.navigation;

import java.util.ArrayList;
import java.util.HashMap;

public class Navigator {

    private HashMap<String, Menu> navigation;
    private ArrayList<String> previousMenus;

    public Navigator() {
        navigation = new HashMap<>();
        previousMenus = new ArrayList<>();
    }

    public void goTo(String destination){
        updateCurrentMenu(destination.toLowerCase());
        navigation.get(destination.toLowerCase()).show(this);
    }

    public Menu goManuelTo(String destination){
        updateCurrentMenu(destination.toLowerCase());
        return navigation.get(destination.toLowerCase());
    }

    public void goBack(){
        int lastIndex = previousMenus.size()-1;
        setCurrentMenu(previousMenus.get(lastIndex));
        previousMenus.remove(lastIndex);
        navigation.get("currentMenu").show(this);
    }

    public void addDestination(String title, Menu menu){
        navigation.put(title.toLowerCase(), menu);
    }

    private void updateCurrentMenu(String name){
        if (!navigation.get("currentMenu").equals(navigation.get(name))) {
            previousMenus.add(navigation.get("currentMenu").getName().toLowerCase());
            navigation.put("currentMenu", navigation.get(name));
        }
    }

    public void setCurrentMenu(String name){
        navigation.put("currentMenu", navigation.get(name.toLowerCase()));
    }

}
