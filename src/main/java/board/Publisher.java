package board;

public class Publisher {
    public String name;
    public float funds;

    public Publisher(String name, float funds) {
        this.name = name;
        this.funds = funds;
    }

    public String getName() {
        return name;
    }

    public float getFunds() {
        return funds;
    }
}
