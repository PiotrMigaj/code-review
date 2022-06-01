package eu.softak.course.quality;

import java.util.List;

public class ExpensesExcelReportDataProvider {

	public static String sheetName() {
		return "Bilans";
	}

	public static List<String> columnNames() {
		return List.of("Mc", "Przychód", "Mieszkanie", "Wyżywienie", "Transport", "Inne");
	}

	@SuppressWarnings("checkstyle:MagicNumber")
	private static List<ExpenseRow> expenseRowList = List.of(
			new ExpenseRow("Styczeń", 4000.0, 1100.0, null, null),
			new ExpenseRow("Luty", 4000.0, 1010.0, null, null)
	);

	public static List<ExpenseRow> getData() {
		return expenseRowList;
	}
}
