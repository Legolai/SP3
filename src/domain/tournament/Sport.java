package domain.tournament;

import java.util.ArrayList;

public class Sport {
    public final String SPORTNAME;
    private final ArrayList<String> rules;


    public Sport(String SPORTNAME) {
        this.SPORTNAME = SPORTNAME;
        rules = new ArrayList<>();
    }

    public Sport(String SPORTNAME, ArrayList<String> rules) {
        this.SPORTNAME = SPORTNAME;
        this.rules = rules;
    }

    public void addRule(String a) {     // scanner to write stuff should not be here
        rules.add(a);
    }

    public String getSPORTNAME() {
        return SPORTNAME;
    }

    public ArrayList<String> getRules() {
        return rules;
    }
}
