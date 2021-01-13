package jpgtopdf;

public class Argument {
    public Folder getOutput() {
        return output;
    }

    public Folder getSource() {
        return source;
    }

    public Argument(String[] args) {
        if (args != null && args.length == 2 && !args[0].isEmpty() && !args[1].isEmpty()) {
            this.source = new Folder(args[0]);
            this.output = new Folder(args[1]);
        } else {
            throw new RuntimeException("Указаны неверные параметры");
        }
    }
    private final Folder output;
    private final Folder source;
}