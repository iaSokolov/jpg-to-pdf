package jpgtopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Image;

public class Comix {
    private String name;
    private List<String> image;

    public Comix(String name, List<String> image) {
        this.name = name;
        this.image = image;
    }

    public void save(String path) throws IOException, DocumentException {
        List<String> fileNameList = this.image
                .stream()
                .map(file -> new jpgtopdf.Image(file))
                .sorted((o1, o2) -> (o1.getPriox() - o2.getPriox()))
                .map(image -> image.getPath())
                .collect(Collectors.toList());

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(path, String.format("%s.pdf", this.name))));
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
    }
}
