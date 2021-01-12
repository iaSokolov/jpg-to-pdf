package jpgtopdf;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String path;

    public Catalog(String path) {
        this.path = path;
    }

    public ArrayList<Comix> getComix() {
        ArrayList<Comix> comixList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();

        File root = new File(this.path);
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                Catalog catalog = new Catalog(file.getPath());
                comixList.addAll(catalog.getComix());
            }
            else {
                if (FilenameUtils.isExtension(file.getName(), "jpg")) {
                    imageList.add(file.getPath());
                }
            }
        }
        if (!imageList.isEmpty()) {
            comixList.add(new Comix(root.getName(), imageList));
        }
        return comixList;
    }
}
