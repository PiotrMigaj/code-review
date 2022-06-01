package eu.softak.course.quality;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class StyleUtils {

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

	static XSSFCellStyle getHeaderStyle(Workbook workBook, StyleParams styleParams) {
		XSSFFont font = (XSSFFont) workBook.createFont();
		font.setBold(true);
		font.setColor(styleParams.getHeaderFontColor());
		XSSFCellStyle style = getXssfCellStyle(workBook, styleParams.getHeaderColor());
		style.setFont(font);
		return style;
	}

	static XSSFCellStyle getDataStyle(Workbook workBook, StyleParams styleParams) {
		return getXssfCellStyle(workBook, styleParams.getDataColor());
	}
}
