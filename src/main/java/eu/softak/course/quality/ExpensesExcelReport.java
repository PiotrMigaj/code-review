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
	private final ReportDataProvider reportDataProvider;

	public ExpensesExcelReport(ReportDataProvider reportDataProvider) {
		this.reportDataProvider = reportDataProvider;
	}

	public ByteArrayOutputStream create(StyleParams styleParams) throws IOException {
		Workbook workBook = new SXSSFWorkbook(-1);
		workBook.getCreationHelper();
		Sheet sheet = workBook.createSheet(reportDataProvider.sheetName());

		addHeader(workBook, sheet, styleParams);
		addRowsWithExpenses(workBook, sheet, styleParams);
		return write(workBook);
	}

	private ByteArrayOutputStream write(Workbook workBook) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workBook.write(outputStream);
		outputStream.close();
		return outputStream;
	}

	private void addRowsWithExpenses(Workbook workBook, Sheet sheet, StyleParams styleParams) {
		int startIndex = 1;
		XSSFCellStyle style = StyleUtils.getDataStyle(workBook, styleParams);

		Row row;
		int columnIndex;
		for (ExpenseRow expenseRow : reportDataProvider.getData()) {
			row = sheet.createRow(startIndex++);
			columnIndex = 0;
			addData(row, columnIndex++, expenseRow.month(), style);
			addData(row, columnIndex++, expenseRow.income(), style);
			addData(row, columnIndex++, expenseRow.rent(), style);
			addData(row, columnIndex++, expenseRow.feeding(), style);
			addData(row, columnIndex++, expenseRow.other(), style);
		}
	}

	private void addHeader(Workbook workBook, Sheet sheet, StyleParams styleParams) {
		XSSFCellStyle style = StyleUtils.getHeaderStyle(workBook, styleParams);
		Row row = sheet.createRow(0);
		List<String> list = reportDataProvider.columnNames();
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
