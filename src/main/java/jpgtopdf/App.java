package jpgtopdf;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException, DocumentException {
        System.out.println("start-of-program");

        String outputPath = "";
        String rootPath = "";
        if (args != null && args.length == 2) {
            rootPath = args[0];
            outputPath = args[1];
        } else {
            System.out.println("error");
        }

        if (outputPath.isEmpty() || rootPath.isEmpty()) {
            System.out.println("error");
            return;
        }
        File root = new File(rootPath);
        Catalog catalog = new Catalog(rootPath);
        ArrayList<Comix> comixList = catalog.getComix();
        for (Comix comix : comixList) {
            comix.save(outputPath);
        }
        System.out.println("end-of-program");
    }
}