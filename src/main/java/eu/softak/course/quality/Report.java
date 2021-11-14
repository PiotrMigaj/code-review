package eu.softak.course.quality;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Report {
	public void create()
	{
		Workbook workBook = new SXSSFWorkbook(-1);

		workBook.getCreationHelper();
		XSSFFont font = (XSSFFont)workBook.createFont();
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
		XSSFCellStyle style1 = (XSSFCellStyle) workBook.createCellStyle();
		style1.setFillForegroundColor( HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex() );
		style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderTop(BorderStyle.THIN);
		style1.setFont(font);
		style1.setWrapText(true);
		XSSFCellStyle style2 = (XSSFCellStyle) workBook.createCellStyle();
		style1.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
		style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderTop(BorderStyle.THIN);
		style1.setFont(font);
		style1.setWrapText(true);
		Sheet sheet = workBook.createSheet("Bilans");
		Row row = sheet.createRow(0);
		Cell data = row.createCell(0);
		data.setCellValue("Mc");
		data.setCellStyle(style1);
		Cell data2 = row.createCell(1);
		data2.setCellValue("Przychód");
		data2.setCellStyle(style1);
		Cell data3 = row.createCell(2);
		data3.setCellValue("Mieszkanie");
		data3.setCellStyle(style1);
		Cell data4 = row.createCell(3);
		data4.setCellValue("Wyżywienie");
		data4.setCellStyle(style1);
		Cell data5 = row.createCell(4);
		data5.setCellValue("Transport");
		data5.setCellStyle(style1);
		Cell data6 = row.createCell(5);
		data6.setCellValue("Inne");
		data6.setCellStyle(style1);
		Row row1 = sheet.createRow(1);
		Cell data11 = row1.createCell(0);
		data11.setCellValue("Styczeń");
		data11.setCellStyle(style2);
		Cell data12 = row1.createCell(1);
		data12.setCellValue(4000.00);
		data12.setCellStyle(style2);
		Cell data13 = row1.createCell(2);
		data13.setCellValue(1100);
		data13.setCellStyle(style2);
		Row row2 = sheet.createRow(2);
		Cell data21 = row2.createCell(0);
		data21.setCellValue("Luty");
		data21.setCellStyle(style2);
		Cell data22 = row2.createCell(1);
		data22.setCellValue(4000.0);
		data22.setCellStyle(style2);
		Cell data23 = row2.createCell(2);
		data23.setCellValue(1010);
		data23.setCellStyle(style2);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File("/raport.xlsx"));
			workBook.write(outputStream);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Report().create();
		System.out.println("Hello World!");
	}
}
