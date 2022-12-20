package com.luxoft.exporter.builder;

import org.apache.poi.xssf.usermodel.XSSFFont;

public class FontBuilder {
    private XSSFFont font;

    public FontBuilder builder(XSSFFont font) {
        this.font = font;
        this.font.setFontHeight(10);
        this.font.setFontName("Book Antiqua");
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
