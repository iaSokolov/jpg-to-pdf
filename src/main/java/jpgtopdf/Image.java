package jpgtopdf;

public class Image {
    private String path;
    private Integer priox;

    public Image(String path) {
        String prioxStr = path.replaceAll("[^\\d]", "");
        try {
            priox = Integer.parseInt(prioxStr);
        }
        catch (Exception ex) {
            priox = 0;
        }
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public Integer getPriox() {
        return priox;
    }
}
