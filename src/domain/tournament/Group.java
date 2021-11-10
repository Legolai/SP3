package domain.tournament;

import java.util.ArrayList;

public class Group {
    private ArrayList<TournamentTeam> members;

    public Group() {
        members = new ArrayList<>();
    }

    public Group(ArrayList<TournamentTeam> members) {
        this.members = members;
    }


    public ArrayList<TournamentTeam> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<TournamentTeam> a) {
        members = a;
    }

    public void addMember(TournamentTeam a) {
        members.add(a);
    }


    public void sortRanking() {
    }   // should this be here?

}
