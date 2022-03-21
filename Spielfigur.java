package spiel;

public class Spielfigur {
    private int id;
    public String rang;
    public String test;

    public Spielfigur(int id, String rang) {
        this.id = id;
        this.rang = rang;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getRang() {
        return this.rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }
}
