package eu.softak.course.quality;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

class ExpensesExcelReportTest {

	private ExpensesExcelReport sut = new ExpensesExcelReport(new ExpensesExcelReportDataProvider());

	@Test
	void shouldCreateExcelReport() throws IOException {
		//given
		//when
		ByteArrayOutputStream result = sut.create(new StyleParams());
		//then
		assertThat(result.size()).isGreaterThanOrEqualTo(3650);
	}

	@Test
	void shouldCreateReportWithOneSheet() throws IOException {
		//given
		int howManySheets;
		//when
		ByteArrayOutputStream result = sut.create(new StyleParams());
		try (InputStream createdReport = new ByteArrayInputStream(result.toByteArray())) {
			try (Workbook workbook = new XSSFWorkbook(createdReport)) {
				howManySheets = workbook.getNumberOfSheets();
			}
		}
		//then
		assertThat(howManySheets).isEqualTo(1);
	}

	@Test
	void shouldCrateReportWithDefinedColumnsAsHeader() throws IOException {
		//given
		int howManyColumns;
		List<String> expectedColumns = List.of("Mc", "Przychód", "Mieszkanie", "Wyżywienie", "Transport", "Inne");
		//when
		ByteArrayOutputStream result = sut.create(new StyleParams());
		try (InputStream createdReport = new ByteArrayInputStream(result.toByteArray())) {
			try (Workbook workbook = new XSSFWorkbook(createdReport)) {
				Row headerRow = workbook.getSheetAt(0).getRow(0);
				howManyColumns = headerRow.getLastCellNum();
				for (int i = 0; i < expectedColumns.size(); i++) {
					assertThat(headerRow.getCell(i).getStringCellValue()).isEqualTo(expectedColumns.get(i));
				}
			}
		}
		//then
		assertThat(howManyColumns).isEqualTo(6);
	}

	@Test
	void shouldFillReportWithSomeData() throws IOException {
		//given
		int howManyRows;
		//when
		ByteArrayOutputStream result = sut.create(new StyleParams());
		try (InputStream createdReport = new ByteArrayInputStream(result.toByteArray())) {
			try (Workbook workbook = new XSSFWorkbook(createdReport)) {
				Row dataRow = workbook.getSheetAt(0).getRow(1);
				howManyRows = workbook.getSheetAt(0).getLastRowNum();
				assertThat(dataRow.getCell(0).getStringCellValue()).isNotBlank();
				assertThat(dataRow.getCell(2).getNumericCellValue()).isPositive();
			}
		}
		//then
		assertThat(howManyRows).isEqualTo(2);
	}
}