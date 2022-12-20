package com.luxoft.exporter.builder;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class StyleBuilder {
    private XSSFCellStyle style;

    public StyleBuilder builder(XSSFCellStyle style) {
        this.style = style;
        return this;
    }

    public StyleBuilder foreGround(short bg) {
        style.setFillForegroundColor(bg);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return this;
    }

    public StyleBuilder verticalCenter() {
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return this;
    }

    public StyleBuilder horizontalCenter() {
        style.setAlignment(HorizontalAlignment.CENTER);
        return this;
    }

    public StyleBuilder borderBottom(BorderStyle border) {
        style.setBorderBottom(border);
        return this;
    }

    public StyleBuilder dataFormat(short fmt) {
        style.setDataFormat(fmt);
        return this;
    }

    public StyleBuilder font(XSSFFont font) {
        style.setFont(font);
        return this;
    }

    public XSSFCellStyle build() {
        return style;
    }
}
