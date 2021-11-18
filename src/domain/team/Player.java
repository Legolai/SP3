package domain.team;

public class Player {
    private final int ID;
    private String name;

    public Player(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
