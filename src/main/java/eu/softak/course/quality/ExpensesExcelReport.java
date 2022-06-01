package eu.softak.course.quality;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExpensesExcelReport {
	public ByteArrayOutputStream create() throws IOException {
		Workbook workBook = new SXSSFWorkbook(-1);
		workBook.getCreationHelper();
		Sheet sheet = workBook.createSheet(ExpensesExcelReportDataProvider.sheetName());

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

		Row row;
		int columnIndex;
		for (ExpenseRow expenseRow : ExpensesExcelReportDataProvider.getData()) {
			row = sheet.createRow(startIndex++);
			columnIndex = 0;
			addData(row, columnIndex++, expenseRow.month(), style);
			addData(row, columnIndex++, expenseRow.income(), style);
			addData(row, columnIndex++, expenseRow.rent(), style);
			addData(row, columnIndex++, expenseRow.feeding(), style);
			addData(row, columnIndex++, expenseRow.other(), style);
		}
	}

	private void addHeader(Workbook workBook, Sheet sheet) {
		XSSFCellStyle style = StyleUtils.getHeaderStyle(workBook);
		Row row = sheet.createRow(0);
		List<String> list = ExpensesExcelReportDataProvider.columnNames();
		for (int i = 0; i < list.size(); i++) {
			addData(row, i, list.get(i), style);
		}
	}

	private void addData(Row row, int i, Double value, XSSFCellStyle style) {
		if (value == null) {
			return;
		}
		Cell data = row.createCell(i);
		data.setCellValue(value);
		data.setCellStyle(style);
	}

	private void addData(Row row, int i, String value, XSSFCellStyle style) {
		Cell data = row.createCell(i);
		data.setCellValue(value);
		data.setCellStyle(style);
	}

}
