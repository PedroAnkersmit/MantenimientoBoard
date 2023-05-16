package board;

public class Publisher {
    public String name;
    public double funds;

    public Publisher(String name, double funds) {
        this.name = name;
        this.funds = funds;
    }

    public String getName() {
        return name;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
}
