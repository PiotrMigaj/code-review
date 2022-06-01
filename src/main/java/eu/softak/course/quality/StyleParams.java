package eu.softak.course.quality;

import org.apache.poi.hssf.util.HSSFColor;

public class StyleParams {

	private short dataColor = HSSFColor.HSSFColorPredefined.YELLOW.getIndex();
	private short headerColor = HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex();
	private short headerFontColor = HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex();

	public short getDataColor() {
		return dataColor;
	}

	public void setDataColor(short dataColor) {
		this.dataColor = dataColor;
	}

	public short getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(short headerColor) {
		this.headerColor = headerColor;
	}

	public short getHeaderFontColor() {
		return headerFontColor;
	}

	public void setHeaderFontColor(short headerFontColor) {
		this.headerFontColor = headerFontColor;
	}
}
