package marco.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import marco.pojo.workInfo;

public class poiExcel {

	public static void write(List<workInfo> list, File file, String zhiwei) throws Exception {

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet(zhiwei);

		// 保存为Excel文件
		FileOutputStream out = null;

		out = new FileOutputStream(file);

		int i = 0;

		for (workInfo wInfo : list) {
			Row row = sheet.createRow(i);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(wInfo.getGongsi());

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(wInfo.getLianxidianhua());

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(wInfo.getLianxiren());

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(wInfo.getRiqi());

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(wInfo.getShiqu());

			Cell cell5 = row.createCell(5);
			cell5.setCellValue(wInfo.getXinzi());

			Cell cell6 = row.createCell(6);
			cell6.setCellValue(wInfo.getZhiwei());

			Cell cell7 = row.createCell(7);
			cell7.setCellValue(wInfo.getZhiweijianjie());

			i++;

		}

		wb.write(out);

		out.close();

	}

}
