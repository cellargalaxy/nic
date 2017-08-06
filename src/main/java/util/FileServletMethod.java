package util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class FileServletMethod {

    public static void sendFile(HttpServletResponse response, File file, boolean isDownload, String fileName) throws IOException {
        if (isDownload) {
            response.reset();
            response.setContentType("application/x-msdownload");
            if (fileName != null)
                response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        }

        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(file);
            int len;
            byte[] bs = new byte[1024 * 10];
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) try {
                inputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (outputStream != null) try {
                outputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
