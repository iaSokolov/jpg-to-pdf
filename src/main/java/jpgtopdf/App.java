package jpgtopdf;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class App {
    private final Argument argument;

    public App(Argument argument) {
        this.argument = argument;
    }

    private void execute() {

        try {
            for (Comix comix : new Catalog(argument.getSource()).getComix()) {
                comix.save(argument.getOutput());
            }
        } catch (Exception ex) {
            Log.out(String.format("ошибка:%s", ex.getMessage()));
        }

    }

    public static void main(String[] args) throws IOException, DocumentException {
        Log.out("запуск программы");
        try {
            new App(new Argument(args)).execute();
        } catch (Exception ex) {
            Log.out(String.format("ошибка:%s", ex.getMessage()));
        }
        Log.out("программа выполнена");
    }
}