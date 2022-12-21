package com.luxoft.exporter.builder;

import org.apache.poi.xssf.usermodel.XSSFFont;

public class FontBuilder {
    private static final String DEFAULT_FONT_NAME = "Book Antiqua";
    private static final int DEFAULT_FONT_SIZE = 10;
    private XSSFFont font;

    public FontBuilder builder(XSSFFont font) {
        this.font = font;
        this.font.setFontHeight(DEFAULT_FONT_SIZE);
        this.font.setFontName(DEFAULT_FONT_NAME);
        return this;
    }

    public FontBuilder bold(boolean bold) {
        font.setBold(bold);
        return this;
    }

    public FontBuilder italic(boolean italic) {
        font.setItalic(italic);
        return this;
    }

    public FontBuilder color(short color) {
        font.setColor(color);
        return this;
    }

    public XSSFFont build() {
        return font;
    }
}
