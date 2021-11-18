package ui.navigation;

import java.util.ArrayList;
import java.util.HashMap;

public class Navigator {

    private final HashMap<String, Menu> navigation;
    private final ArrayList<String> previousMenus;

    public Navigator() {
        navigation = new HashMap<>();
        previousMenus = new ArrayList<>();
    }

    public void goTo(String destination) {
        updateCurrentMenu(destination.toLowerCase());
        navigation.get(destination.toLowerCase()).show(this);
    }

    public Menu goManuelTo(String destination) {
        updateCurrentMenu(destination.toLowerCase());
        return navigation.get(destination.toLowerCase());
    }

    public void goBack() {
        int lastIndex = previousMenus.size() - 1;
        setCurrentMenu(previousMenus.get(lastIndex));
        previousMenus.remove(lastIndex);
        navigation.get("currentMenu").show(this);
    }

    public void addDestination(Menu menu) {
        navigation.put(menu.getName().toLowerCase(), menu);
    }

    private void updateCurrentMenu(String name) {
        if (!navigation.get("currentMenu").equals(navigation.get(name))) {
            previousMenus.add(navigation.get("currentMenu").getName().toLowerCase());
            navigation.put("currentMenu", navigation.get(name));
        }
    }

    public void setCurrentMenu(String name) {
        navigation.put("currentMenu", navigation.get(name.toLowerCase()));
    }

}
