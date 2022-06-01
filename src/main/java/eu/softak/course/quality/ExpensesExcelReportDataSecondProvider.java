package eu.softak.course.quality;

import java.util.List;

public class ExpensesExcelReportDataSecondProvider implements ReportDataProvider {

	@Override
	public String sheetName() {
		return "Wydatki";
	}

	@Override
	public List<String> columnNames() {
		return List.of("Mc", "Przychód", "Mieszkanie", "Wyżywienie", "Transport", "Inne");
	}


	@SuppressWarnings("checkstyle:MagicNumber")
	private static List<ExpenseRow> expenseRowList = List.of(
			new ExpenseRow("Styczeń", 5000.0, 1100.0, null, null),
			new ExpenseRow("Luty", 6000.0, 1010.0, null, null),
			new ExpenseRow("Marzec", 6000.0, 1010.0, null, null)
	);

	@Override
	public List<ExpenseRow> getData() {
		return expenseRowList;
	}
}
