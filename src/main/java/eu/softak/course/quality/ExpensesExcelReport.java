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
		Sheet sheet = workBook.createSheet("Bilans");

		addHeader(workBook, sheet);
		addRowsWithExpenses(workBook, sheet);
		saveAs(workBook);
	}

	private void saveAs(Workbook workBook) {
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

	private void addRowsWithExpenses(Workbook workBook, Sheet sheet) {
		int startIndex = 1;
		XSSFCellStyle style = getXssfCellStyle(workBook, HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
		Row row = sheet.createRow(startIndex++);
		addData(row, 0, "Styczeń", style);
		List<Double> data = getDataFirstRow();
		for (int i = 0; i < data.size(); i++) {
			addData(row, i + 1, data.get(i), style);
		}

		row = sheet.createRow(startIndex++);
		addData(row, 0, "Luty", style);
		data = getDataSecondRow();
		for (int i = 0; i < data.size(); i++) {
			addData(row, i + 1, data.get(i), style);
		}
	}

	private void addHeader(Workbook workBook, Sheet sheet) {
		XSSFFont font = (XSSFFont) workBook.createFont();
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
		XSSFCellStyle style = getXssfCellStyle(workBook, HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
		style.setFont(font);
		Row row = sheet.createRow(0);
		List<String> list = List.of("Mc", "Przychód", "Mieszkanie", "Wyżywienie", "Transport", "Inne");
		for (int i = 0; i < list.size(); i++) {
			addData(row, i, list.get(i), style);
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

	private void addData(Row row, int i, double v, XSSFCellStyle style) {
		Cell data = row.createCell(i);
		data.setCellValue(v);
		data.setCellStyle(style);
	}

	private void addData(Row row, int i, String value, XSSFCellStyle style) {
		Cell data = row.createCell(i);
		data.setCellValue(value);
		data.setCellStyle(style);
	}

	private XSSFCellStyle getXssfCellStyle(Workbook workBook, short color) {
		XSSFCellStyle style = (XSSFCellStyle) workBook.createCellStyle();
		style.setFillForegroundColor(color);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setWrapText(true);
		return style;
	}

	public static void main(String[] args) {
		new ExpensesExcelReport().create();
		System.out.println("Hello World!");
	}
}
