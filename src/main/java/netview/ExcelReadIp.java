package netview;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/26.
 */
public class ExcelReadIp {

    public static LinkedList<Host> readExcelIps(File file) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            LinkedList<Host> hosts = new LinkedList<Host>();
            for (int i = 1; i < sheet.getRows(); i++) {
                Cell[] cells = sheet.getRow(i);
                Host host;
                if (cells.length == 5) {
                    //					address					building				floor					model					name
                    host = new Host(cells[0].getContents().trim(), cells[1].getContents().trim(), cells[2].getContents().trim(), cells[3].getContents().trim(), cells[4].getContents().trim());
                } else {
                    //					address					building				floor					model					name
                    host = new Host(cells[0].getContents().trim(), cells[1].getContents().trim(), cells[2].getContents().trim(), cells[3].getContents().trim(), null);
                }
                hosts.add(host);
            }
            return hosts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }


}
