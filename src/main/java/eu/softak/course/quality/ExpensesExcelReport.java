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
import java.util.List;

public class ExpensesExcelReport {
	public void create() {
		Workbook workBook = new SXSSFWorkbook(-1);

		workBook.getCreationHelper();
		XSSFFont font = (XSSFFont) workBook.createFont();
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());

		XSSFCellStyle style = getXssfCellStyle(workBook, HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
		style.setFont(font);


		Sheet sheet = workBook.createSheet("Bilans");
		int startIndex = 0;

		Row row = sheet.createRow(startIndex++);

		List<String> list = List.of("Mc", "Przychód", "Mieszkanie", "Wyżywienie", "Transport", "Inne");
		for (int i = 0; i < list.size(); i++) {
			addData(row, i, list.get(i), style);
		}

		style = getXssfCellStyle(workBook, HSSFColor.HSSFColorPredefined.YELLOW.getIndex());

		Row row1 = sheet.createRow(startIndex++);
		addData(row1, 0, "Styczeń", style);
		List<Double> data = getDataFirstRow();
		for (int i = 0; i < data.size(); i++) {
			addData(row1, i + 1, data.get(i), style);
		}

		Row row2 = sheet.createRow(startIndex++);
		addData(row2, 0, "Luty", style);
		data = getDataSecondRow();
		for (int i = 0; i < data.size(); i++) {
			addData(row2, i + 1, data.get(i), style);
		}

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File("raport.xlsx"));
			workBook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("checkstyle:MagicNumber")
	private List<Double> getDataFirstRow() {
		return List.of(4000.0, 1100.0);
	}

	@SuppressWarnings("checkstyle:MagicNumber")
	private List<Double> getDataSecondRow() {
		return List.of(4000.0, 1010.0);
	}

	private void addData(Row row1, int i, double v, XSSFCellStyle style2) {
		Cell data12 = row1.createCell(i);
		data12.setCellValue(v);
		data12.setCellStyle(style2);
	}

	private void addData(Row row, int i, String Mc, XSSFCellStyle style1) {
		Cell data = row.createCell(i);
		data.setCellValue(Mc);
		data.setCellStyle(style1);
	}

	private XSSFCellStyle getXssfCellStyle(Workbook workBook, short color) {
		XSSFCellStyle style1 = (XSSFCellStyle) workBook.createCellStyle();
		style1.setFillForegroundColor(color);
		style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderTop(BorderStyle.THIN);
		style1.setWrapText(true);
		return style1;
	}

	public static void main(String[] args) {
		new ExpensesExcelReport().create();
		System.out.println("Hello World!");
	}
}
