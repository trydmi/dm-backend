package com.luxoft.exporter;

import com.luxoft.exporter.builder.FontBuilder;
import com.luxoft.exporter.builder.StyleBuilder;
import com.luxoft.model.RiservaNetta;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Slf4j
public class RiservaNettaExporter {
    private static final String DATA_FORMAT = "#,##0";

    private final XSSFWorkbook workbook;
    private final RiservaNetta riservaNetta;
    private XSSFSheet sheet;

    public RiservaNettaExporter(RiservaNetta riservaNetta) {
        this.riservaNetta = riservaNetta;
        workbook = new XSSFWorkbook();
    }

    public void export(HttpServletResponse response) throws IOException {
        writeCells();
        borderColumn();
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
        autoSize();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        log.info("IN export - report exported successfully");
    }

    private void writeCells() {
        sheet = workbook.createSheet("Riserva HTC-HTCS");
        sheet.setDisplayGridlines(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DataFormat format = workbook.createDataFormat();

        XSSFFont italicFont = new FontBuilder().builder(workbook.createFont())
                .italic(true)
                .build();

        XSSFFont boldItalicFont = new FontBuilder().builder(workbook.createFont())
                .bold(true)
                .italic(true)
                .build();

        XSSFFont boldFont = new FontBuilder().builder(workbook.createFont())
                .bold(true)
                .build();

        XSSFFont boldWhiteFont = new FontBuilder().builder(workbook.createFont())
                .bold(true)
                .color(HSSFColor.HSSFColorPredefined.WHITE.getIndex())
                .build();

        XSSFFont basicFont = new FontBuilder().builder(workbook.createFont()).build();

        XSSFCellStyle boldItalicStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(boldItalicFont)
                .verticalCenter()
                .build();

        XSSFCellStyle dateStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(boldFont)
                .horizontalCenter()
                .verticalCenter()
                .build();

        XSSFCellStyle titleCenterStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(basicFont)
                .horizontalCenter()
                .verticalCenter()
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFCellStyle titleLeftFilledStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(boldWhiteFont)
                .verticalCenter()
                .foreGround(IndexedColors.DARK_BLUE.index)
                .build();

        XSSFCellStyle numberBoldFilledStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(boldWhiteFont)
                .verticalCenter()
                .horizontalCenter()
                .foreGround(IndexedColors.DARK_BLUE.index)
                .dataFormat(format.getFormat(DATA_FORMAT))
                .build();

        XSSFCellStyle titleLeftBoldStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(boldFont)
                .verticalCenter()
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFCellStyle numberBoldStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(boldFont)
                .horizontalCenter()
                .verticalCenter()
                .dataFormat(format.getFormat(DATA_FORMAT))
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFCellStyle titleLeftItalicStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(italicFont)
                .verticalCenter()
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFCellStyle numberItalicStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(italicFont)
                .verticalCenter()
                .horizontalCenter()
                .dataFormat(format.getFormat(DATA_FORMAT))
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFCellStyle titleLeftStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(basicFont)
                .verticalCenter()
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFCellStyle numberStyle = new StyleBuilder().builder(workbook.createCellStyle())
                .font(basicFont)
                .verticalCenter()
                .horizontalCenter()
                .dataFormat(format.getFormat(DATA_FORMAT))
                .borderBottom(BorderStyle.DOTTED)
                .build();

        XSSFRow row = sheet.createRow(2);
        createCell(row, 2, "RISERVA NETTA", boldItalicStyle);

        row = sheet.createRow(4);
        createCell(row, 3, riservaNetta.getDate().format(formatter), dateStyle);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

        row = sheet.createRow(5);
        createCell(row, 3, "Positive Reserve", titleCenterStyle);
        createCell(row, 4, "Negative Reserve", titleCenterStyle);
        createCell(row, 5, "Net Reserve", titleCenterStyle);

        row = sheet.createRow(6);
        createCell(row, 2, "Totale HTC", titleLeftFilledStyle);
        createFormulaCell(row, 3, "SUM(D8+D12+D16)", numberBoldFilledStyle);
        createFormulaCell(row, 4, "SUM(E8+E12+E16)", numberBoldFilledStyle);
        createFormulaCell(row, 5, "SUM(F8+F12+F16)", numberBoldFilledStyle);

        row = sheet.createRow(7);
        createCell(row, 2, "Govies DM", titleLeftBoldStyle);
        createFormulaCell(row, 3, "SUM(D9:D11)", numberBoldStyle);
        createFormulaCell(row, 4, "SUM(E9:E11)", numberBoldStyle);
        createFormulaCell(row, 5, "SUM(F9:F11)", numberBoldStyle);

        row = sheet.createRow(8);
        createCell(row, 2, "di cui ORD", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPrOrd(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNrOrd(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D9+E9)", numberItalicStyle);

        row = sheet.createRow(9);
        createCell(row, 2, "di cui EC", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPrEc(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNrEc(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D10+E10)", numberItalicStyle);

        row = sheet.createRow(10);
        createCell(row, 2, "di cui GRM", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPrGrm(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNrGrm(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D11+E11)", numberItalicStyle);

        row = sheet.createRow(11);
        createCell(row, 2, "Govies EM", titleLeftBoldStyle);
        createFormulaCell(row, 3, "SUM(D13:D15)", numberBoldStyle);
        createFormulaCell(row, 4, "SUM(E13:E15)", numberBoldStyle);
        createFormulaCell(row, 5, "SUM(F13:F15)", numberBoldStyle);

        row = sheet.createRow(12);
        createCell(row, 2, "di cui ORD", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesemPrOrd(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesemNrOrd(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D13:E13)", numberItalicStyle);

        row = sheet.createRow(13);
        createCell(row, 2, "di cui GRM", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesemPrGrm(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesemNrGrm(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D14:E14)", numberItalicStyle);

        row = sheet.createRow(14);
        createCell(row, 2, "di cui EC", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesemPrEc(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesemNrEc(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D15+E15)", numberItalicStyle);

        row = sheet.createRow(15);
        createCell(row, 2, "Corp/Fin/ABS", titleLeftBoldStyle);
        createFormulaCell(row, 3, "SUM(D17:D18)", numberBoldStyle);
        createFormulaCell(row, 4, "SUM(E17:E18)", numberBoldStyle);
        createFormulaCell(row, 5, "SUM(F17:F18)", numberBoldStyle);

        row = sheet.createRow(16);
        createCell(row, 2, "di cui ORD", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getCorpPrOrd(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getCorpNrOrd(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D17:E17)", numberItalicStyle);

        row = sheet.createRow(17);
        createCell(row, 2, "di cui EC", titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getCorpPrEc(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getCorpNrEc(), numberItalicStyle);
        createFormulaCell(row, 5, "SUM(D18+E18)", numberItalicStyle);

        row = sheet.createRow(19);
        createCell(row, 2, "Totale HTCS", titleLeftFilledStyle);
        createFormulaCell(row, 3, "SUM(D21:D23)", numberBoldFilledStyle);
        createFormulaCell(row, 4, "SUM(E21:E23)", numberBoldFilledStyle);
        createFormulaCell(row, 5, "SUM(F21:F23)", numberBoldFilledStyle);

        row = sheet.createRow(20);
        createCell(row, 2, "Govies DM", titleLeftStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPr(), numberStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNr(), numberStyle);
        createFormulaCell(row, 5, "SUM(D21+E21)", numberStyle);

        row = sheet.createRow(21);
        createCell(row, 2, "Govies EM", titleLeftStyle);
        createCell(row, 3, riservaNetta.getGoviesemPr(), numberStyle);
        createCell(row, 4, riservaNetta.getGoviesemNr(), numberStyle);
        createFormulaCell(row, 5, "SUM(D22+E22)", numberStyle);

        row = sheet.createRow(22);
        createCell(row, 2, "Corp/Fin/ABS", titleLeftStyle);
        createCell(row, 3, riservaNetta.getCorpPr(), numberStyle);
        createCell(row, 4, riservaNetta.getCorpNr(), numberStyle);
        createFormulaCell(row, 5, "SUM(D23+E23)", numberStyle);

        sheet.createFreezePane(3, 0);
    }

    private void borderColumn() {
        CellRangeAddress fillSidesRegion = new CellRangeAddress(5, 22, 5, 5);
        RegionUtil.setBorderLeft(BorderStyle.THICK, fillSidesRegion, sheet);
        RegionUtil.setBorderRight(BorderStyle.THICK, fillSidesRegion, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.DARK_BLUE.index, fillSidesRegion, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.DARK_BLUE.index, fillSidesRegion, sheet);

        CellRangeAddress fillTopRegion = new CellRangeAddress(5, 5, 5, 5);
        RegionUtil.setBorderTop(BorderStyle.THICK, fillTopRegion, sheet);
        RegionUtil.setTopBorderColor(IndexedColors.DARK_BLUE.index, fillTopRegion, sheet);

        CellRangeAddress fillBottomRegion = new CellRangeAddress(22, 22, 5, 5);
        RegionUtil.setBorderBottom(BorderStyle.THICK, fillBottomRegion, sheet);
        RegionUtil.setBottomBorderColor(IndexedColors.DARK_BLUE.index, fillBottomRegion, sheet);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof String s) {
            cell.setCellValue(s);
        } else if (value instanceof Double d) {
            cell.setCellValue(d);
        }
        cell.setCellStyle(style);
    }

    private void createFormulaCell(Row row, int columnCount, String formula, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);
    }

    private void autoSize() {
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
