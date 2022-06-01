package eu.softak.course.quality;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.*;
import java.util.List;

public class ExpensesExcelReport {
	public ByteArrayOutputStream create() throws IOException {
		Workbook workBook = new SXSSFWorkbook(-1);
		workBook.getCreationHelper();
		Sheet sheet = workBook.createSheet("Bilans");

		addHeader(workBook, sheet);
		addRowsWithExpenses(workBook, sheet);
		return write(workBook);
	}

	private ByteArrayOutputStream write(Workbook workBook) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workBook.write(outputStream);
		outputStream.close();
		return outputStream;
	}

	private void addRowsWithExpenses(Workbook workBook, Sheet sheet) {
		int startIndex = 1;
		XSSFCellStyle style = StyleUtils.getDataStyle(workBook);
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
		XSSFCellStyle style = StyleUtils.getHeaderStyle(workBook);
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

}
