package nic;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cellargalaxy on 2017/5/4.
 */
public class PicturePool{
	private static final String pictureKind = Configuration.getPictureKind().toLowerCase();
	private static final File root = new File(Configuration.getPictureRootPath());
	private static final ArrayList<File> pictures = new ArrayList<File>();
	
	static {
		fillPictures(root.listFiles());
	}
	
	public static File getPictureFile(int index) {
		if (pictures.size() == 0) {
			return null;
		} else {
			index = index % pictures.size();
			return pictures.get(index);
		}
	}
	
	public static File getPictureFile(double index) {
		if (pictures.size() == 0) {
			return null;
		} else {
			return pictures.get((int) (index * pictures.size()));
		}
	}
	
	public static int pictureCount() {
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