package nic;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/5/4.
 */
public class PicturePool extends HttpServlet {
    private static final String pictureKind = Configuration.getPictureKind().toLowerCase();
    private static File root;
    private static LinkedList<File> pictures;

    static {
        root = new File(Configuration.getPictureRootPath());
        pictures = new LinkedList<File>();
    }

    public static File getPictureFile() {
        if (pictures.size() <= 0) {
            fillPictures();
        }
        return pictures.poll();
    }

    private static void fillPictures() {
        fillPictures(root.listFiles());
    }

    private static void fillPictures(File[] files) {
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                String ex = file.getName().toLowerCase();
                int i = ex.lastIndexOf('.');
                if (i > -1 && i < ex.length() - 1) {
                    ex = ex.substring(i + 1);
                }
                if (pictureKind.contains(ex)) {
                    pictures.add(file);
                }
            } else {
                fillPictures(file.listFiles());
            }
        }
    }
}
