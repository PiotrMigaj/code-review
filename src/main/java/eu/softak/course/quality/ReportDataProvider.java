package eu.softak.course.quality;

import java.util.List;

public interface ReportDataProvider {
	String sheetName();

	List<String> columnNames();

	List<ExpenseRow> getData();
}
