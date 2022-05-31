package eu.softak.course.quality;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpensesExcelReportTest {

	private ExpensesExcelReport sut = new ExpensesExcelReport();
	private Path fileName = Path.of("raport.xlsx");

	@Test
	@Order(1)
	void shouldCreateExcelReport() throws IOException {
		//given
		Files.deleteIfExists(fileName);
		assertThat(Files.exists(fileName)).isFalse();
		//when
		sut.create();
		//then
		assertThat(Files.exists(fileName)).isTrue();
	}

	@Test
	@Order(2)
	void shouldCreateReportWithOneSheet() throws IOException {
		//given
		//when
		int howManySheets;
		sut.create();
		try (Workbook workbook = new XSSFWorkbook(fileName.toString())) {
			howManySheets = workbook.getNumberOfSheets();
		}
		//then
		assertThat(howManySheets).isEqualTo(1);
	}

	@Test
	@Order(3)
	void shouldCrateReportWithDefinedColumnsAsHeader() throws IOException {
		//given
		int howManyColumns;
		List<String> expectedColumns = List.of("Mc", "Przychód", "Mieszkanie", "Wyżywienie", "Transport", "Inne");
		//when
		sut.create();
		try (Workbook workbook = new XSSFWorkbook(fileName.toString())) {
			Row headerRow = workbook.getSheetAt(0).getRow(0);
			howManyColumns = headerRow.getLastCellNum();
			for (int i = 0; i < expectedColumns.size(); i++) {
				assertThat(headerRow.getCell(i).getStringCellValue()).isEqualTo(expectedColumns.get(i));
			}
		}
		//then
		assertThat(howManyColumns).isEqualTo(6);
	}

	@Test
	@Order(4)
	void shouldFillReportWithSomeData() throws IOException {
		//given
		//when
		int howManyRows;
		sut.create();
		try (Workbook workbook = new XSSFWorkbook(fileName.toString())) {
			Row dataRow = workbook.getSheetAt(0).getRow(1);
			howManyRows = workbook.getSheetAt(0).getLastRowNum();
			assertThat(dataRow.getCell(0).getStringCellValue()).isNotBlank();
			assertThat(dataRow.getCell(2).getNumericCellValue()).isPositive();
		}
		assertThat(howManyRows).isEqualTo(2);
	}
}