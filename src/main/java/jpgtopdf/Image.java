package jpgtopdf;

public class Image {
    private final String path;
    private final Integer priox;

    public Image(String path) {
        String prioxStr = path.replaceAll("[^\\d]", "");
        int parsePriox;
        try {
            parsePriox = Integer.parseInt(prioxStr);
        }
        catch (NumberFormatException ex) {
            parsePriox = 0;
        }
        this.priox = parsePriox;
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public int getPriox() {
        return priox;
    }
}
