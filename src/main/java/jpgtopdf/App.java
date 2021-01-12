package jpgtopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    // первый параметр - источник с какой папки надо смотреть файлики
    // второй параметр - куда надо складывать результаты
    public static void main(String[] args) throws IOException, DocumentException {
        System.out.println("start-of-program");

        String outputPath = "";
        String rootPath = "";
        if (args != null && args.length == 2) {
            outputPath = args[0];
            rootPath = args[1];
            System.out.println("error");
        }

        if (outputPath.isEmpty() || rootPath.isEmpty()) {
            System.out.println("error");
        }

        File root = new File(rootPath);
        String outputFile = String.format("%s/%s.pdf", outputPath, root.getName());

        List<String> fileNameList = Arrays.stream(root.listFiles())
                .filter(file -> FilenameUtils.getExtension(file.getName()).equals("jpg"))
                .map(file -> file.getAbsolutePath())
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
        document.open();

        for (String fileName : fileNameList) {
            document.newPage();
            Image image = Image.getInstance(fileName);
            image.scaleToFit(PageSize.A4);
            image.setBorderWidth(1);
            image.setAbsolutePosition(0, 0);
            document.add(image);
        }
        document.close();
        System.out.println("end-of-program");
    }
}