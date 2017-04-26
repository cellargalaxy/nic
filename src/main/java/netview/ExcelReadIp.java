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
			main:
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell[] cells = sheet.getRow(i);
				for (Cell cell : cells)
					if (cell.getContents() == null || cell.getContents().trim().length() == 0) continue main;
				Host host = new Host(cells[0].getContents(), new Integer(cells[1].getContents()), cells[2].getContents(), cells[3].getContents(), Configuration.getTimes());
				hosts.add(host);
			}
			return hosts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (workbook != null) workbook.close();
		}
	}
}
