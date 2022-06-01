package eu.softak.course.quality;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AppRun {
	public static void main(String[] args) throws IOException {

		extracted(new ExpensesExcelReportDataProvider(), "expense-report.xlsx");
		extracted(new ExpensesExcelReportDataSecondProvider(), "expense-report-second.xlsx");
		System.out.println("Report created!");
	}

	private static void extracted(ReportDataProvider reportDataProvider, String fileName) throws IOException {
		ByteArrayOutputStream report = new ExpensesExcelReport(reportDataProvider).create(new StyleParams());
		try (OutputStream file = new FileOutputStream(fileName)) {
			file.write(report.toByteArray());
		}
	}
}
