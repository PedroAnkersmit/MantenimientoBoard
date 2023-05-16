package board;

public class Advertisement {
    public String title;
    public String text;
    public Publisher user;

    public Advertisement(String title, String text, Publisher user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }
}
