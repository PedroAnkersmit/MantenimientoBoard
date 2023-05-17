package board;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher publisher)) return false;
        return Double.compare(publisher.getFunds(), getFunds()) == 0 && Objects.equals(getName(), publisher.getName());
    }


}
