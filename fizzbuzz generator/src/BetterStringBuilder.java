public class BetterStringBuilder {
    private String string;
    public BetterStringBuilder() {
        string = "";
    }

    public void append(String s) {
        string += s;
    }

    public String toString() {
        return string;
    }
}
