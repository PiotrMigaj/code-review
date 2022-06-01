package eu.softak.course.quality;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class StyleUtils {

	private static final short YELLOW_COLOR = HSSFColor.HSSFColorPredefined.YELLOW.getIndex();
	private static final short GREY_50_PERCENT_COLOR = HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex();
	private static final short BLUE_GREY_COLOR = HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex();

	private static XSSFCellStyle getXssfCellStyle(Workbook workBook, short color) {
		XSSFCellStyle style = (XSSFCellStyle) workBook.createCellStyle();
		style.setFillForegroundColor(color);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setWrapText(true);
		return style;
	}

	static XSSFCellStyle getHeaderStyle(Workbook workBook) {
		XSSFFont font = (XSSFFont) workBook.createFont();
		font.setBold(true);
		font.setColor(BLUE_GREY_COLOR);
		XSSFCellStyle style = getXssfCellStyle(workBook, GREY_50_PERCENT_COLOR);
		style.setFont(font);
		return style;
	}

	static XSSFCellStyle getDataStyle(Workbook workBook) {
		return getXssfCellStyle(workBook, YELLOW_COLOR);
	}
}
