import java.util.ArrayList;

public class Sport {
    public String SPORTNAME;
    private ArrayList<String> rules;


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
    public ArrayList<String> getRules() {
        return rules;
    }
}
