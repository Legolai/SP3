package ui.navigation;

import domain.team.Player;
import domain.team.Team;

import java.util.LinkedHashMap;

public class TeamMenu extends Menu {

    Team team;

    public TeamMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View all team members",
                "Edit Team members",
                "View Stats",
                "Go back"
        });
    }

    @Override
    public void show(Navigator navigation) {

    }

    public void show(Navigator navigation, Team team) {
        clearScreen();
        super.showCustomHeader(team.getName());
        super.showMenu("b");
        this.team = team;
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showTeamMembers(navigation);
            case "2" -> showEditTeamMembers(navigation);
            case "3" -> showTeamStreak(navigation);
            default -> navigation.goBack();
        }
    }

    private void showTeamMembers(Navigator navigation) {
        ui.newLine();
        StringBuilder s = new StringBuilder();
        for (Player pl : team.getTeamMembers()) {
            s.append(pl.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
        }
        ui.println(s.toString());
        ui.newLine();
        ui.waitForUser();
        show(navigation, team);
    }

    private void showTeamStreak(Navigator navigation) {
        ui.newLine();
        LinkedHashMap<String, Integer> streak = team.getHistory().getTeamStreak();
        StringBuilder s = new StringBuilder();
        for (String key : streak.keySet()) {
            s.append(key).append(": ").append(streak.get(key)).append(" | ");
        }
        ui.println(s.substring(0, s.length() - 2));
        ui.waitForUser();
        show(navigation, team);
    }

    private void showEditTeamMembers(Navigator navigation) {
        clearScreen();
        super.showCustomHeader(team.getName());
        ui.printOptions(new String[]{
                "Add member",
                "Remove member",
                "Go back"
        }, "b");
        switch (ui.getUserOption("Select action:", getNumberOfOptions(), "b")) {
            case "1" -> showAddnewMember(navigation);
            case "2" -> showRemoveMember(navigation);
            default -> show(navigation, team);
        }
    }

    private void showAddnewMember(Navigator navigation) {
        team.addMember(new Player(ui.getUserInput("Type the name of the new player:")));
        showEditTeamMembers(navigation);
    }

    private void showRemoveMember(Navigator navigation) {
        StringBuilder s = new StringBuilder();
        int memberNum = 1;
        for (Player pl : team.getTeamMembers()) {
            s.append("(").append(memberNum).append(")").append(pl.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
            ++memberNum;
        }
        ui.println(s.toString());


        String memberIndex = ui.getUserOption("Type the index of the team member (b for back):", team.getTeamMembers().size() + 1, "b");
        if (!memberIndex.equals("b")) {
            try {
                int index = Integer.parseInt(memberIndex);
                ui.newLine();
                Player player = team.getTeamMembers().get(index - 1);
                ui.println("Selected player [" + player.getName() + "]");
                if (ui.getUserOption("Are you sure you want to remove this player? (y/n):", new String[]{"y", "n"}).equalsIgnoreCase("y")) {
                    team.removeMember(player);
                    ui.println("The player " + player.getName() + " has been removed from the team");
                }
            } catch (Exception e) {
                ui.println(e.getMessage());
            }
        }
        ui.waitForUser();
        showEditTeamMembers(navigation);
    }


}
