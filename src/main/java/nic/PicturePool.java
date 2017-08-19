package nic;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/5/4.
 */
public class PicturePool extends HttpServlet {
    private static final String pictureKind = Configuration.getPictureKind().toLowerCase();
    private static final File root= new File(Configuration.getPictureRootPath());
    private static final ArrayList<File> pictures=new ArrayList<File>();
    
    static {
        fillPictures(root.listFiles());
    }
	
	public static File getPictureFile(int index){
	    if (pictures.size()==0) {
		    return null;
	    }else {
		    index=index%pictures.size();
		    return pictures.get(index);
	    }
    }
    
    public static File getPictureFile(double index){
	    if (pictures.size()==0) {
		    return null;
	    }else {
		    return pictures.get((int)(index*pictures.size()));
	    }
    }
    
    public static int pictureCount(){
    	return pictures.size();
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