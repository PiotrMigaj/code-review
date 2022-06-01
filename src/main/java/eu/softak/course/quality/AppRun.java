package eu.softak.course.quality;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AppRun {
	public static void main(String[] args) throws IOException {

		ByteArrayOutputStream report = new ExpensesExcelReport().create();
		try (OutputStream file = new FileOutputStream("expense-report.xlsx")) {
			file.write(report.toByteArray());
		}
		System.out.println("Report created!");
	}
}
