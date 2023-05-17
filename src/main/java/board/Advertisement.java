package board;

import java.util.Objects;

public class Advertisement {
    public String title;
    public String text;
    public Publisher user;

    public Advertisement(String title, String text, Publisher user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Publisher getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertisement)) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(title, that.title) && Objects.equals(text, that.text) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, user);
    }
}
