package com.luxoft.exporter;

import com.luxoft.exporter.builder.FontBuilder;
import com.luxoft.exporter.builder.StyleBuilder;
import com.luxoft.model.RiservaNetta;
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
    private static final String SHEET_NAME = "Riserva HTC-HTCS";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private static final String RISERVA_NETTA_CELL = "RISERVA NETTA";
    private static final String POSITIVE_RESERVE_CELL = "Positive Reserve";
    private static final String NEGATIVE_RESERVE_CELL = "Negative Reserve";
    private static final String NET_RESERVE_CELL = "Net Reserve";
    private static final String TOTALE_HTC_CELL = "Totale HTC";
    private static final String GOVIES_DM_CELL = "Govies DM";
    private static final String DI_CUI_ORD_CELL = "di cui ORD";
    private static final String DI_CUI_EC_CELL = "di cui EC";
    private static final String DI_CUI_GRM_CELL = "di cui GRM";
    private static final String GOVIES_EM_CELL = "Govies EM";
    private static final String CORP_FIN_ABS_CELL = "Corp/Fin/ABS";
    private static final String TOTALE_HTCS_CELL = "Totale HTCS";

    private static final String HTC_PR_FORMULA = "SUM(D8+D12+D16)";
    private static final String HTC_NEG_FORMULA = "SUM(E8+E12+E16)";
    private static final String HTC_NET_FORMULA = "SUM(F8+F12+F16)";
    private static final String GOV_DM_PR_FORMULA = "SUM(D9:D11)";
    private static final String GOV_DM_NEG_FORMULA = "SUM(E9:E11)";
    private static final String GOV_DM_ORD_NET_FORMULA = "SUM(D9+E9)";
    private static final String GOV_DM_EC_NET_FORMULA = "SUM(D10+E10)";
    private static final String GOV_DM_GRM_NET_FORMULA = "SUM(D11+E11)";
    private static final String GOV_DM_NET_FORMULA = "SUM(F9:F11)";
    private static final String GOV_EM_PR_FORMULA = "SUM(D13:D15)";
    private static final String GOV_EM_NEG_FORMULA = "SUM(E13:E15)";
    private static final String GOV_EM_NET_FORMULA = "SUM(F13:F15)";
    private static final String GOV_EM_ORD_NET_FORMULA = "SUM(D13:E13)";
    private static final String GOV_EM_GRM_NET_FORMULA = "SUM(D14:E14)";
    private static final String GOV_EM_EC_NET_FORMULA = "SUM(D15+E15)";
    private static final String CORP_FIN_ABS_PR_FORMULA = "SUM(D17:D18)";
    private static final String CORP_FIN_ABS_NEG_FORMULA = "SUM(E17:E18)";
    private static final String CORP_FIN_ABS_NET_FORMULA = "SUM(F17:F18)";
    private static final String CORP_FIN_ABS_ORD_NET_FORMULA = "SUM(D17:E17)";
    private static final String CORP_FIN_ABS_EC_NET_FORMULA = "SUM(D18+E18)";
    private static final String HTCS_PR_FORMULA = "SUM(D21:D23)";
    private static final String HTCS_NEG_FORMULA = "SUM(E21:E23)";
    private static final String HTCS_NET_FORMULA = "SUM(F21:F23)";
    private static final String HTCS_GOV_DM_NET_FORMULA = "SUM(D21+E21)";
    private static final String HTCS_GOV_EM_NET_FORMULA = "SUM(D22+E22)";
    private static final String HTCS_CORP_FIN_ABS_NET_FORMULA = "SUM(D23+E23)";

    private final XSSFWorkbook workbook;
    private final RiservaNetta riservaNetta;
    private XSSFSheet sheet;

    public RiservaNettaExporter(RiservaNetta riservaNetta) {
        this.riservaNetta = riservaNetta;
        workbook = new XSSFWorkbook();
    }

    public XSSFWorkbook export() throws IOException {
        writeCells();
        borderColumn();
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
        autoSize();

        log.info("IN RiservaNettaExporter.export - export completed");
        return workbook;
    }

    private void writeCells() {
        sheet = workbook.createSheet(SHEET_NAME);
        sheet.setDisplayGridlines(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
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
        createCell(row, 2, RISERVA_NETTA_CELL, boldItalicStyle);

        row = sheet.createRow(4);
        createCell(row, 3, riservaNetta.getDate().format(formatter), dateStyle);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));

        row = sheet.createRow(5);
        createCell(row, 3, POSITIVE_RESERVE_CELL, titleCenterStyle);
        createCell(row, 4, NEGATIVE_RESERVE_CELL, titleCenterStyle);
        createCell(row, 5, NET_RESERVE_CELL, titleCenterStyle);

        row = sheet.createRow(6);
        createCell(row, 2, TOTALE_HTC_CELL, titleLeftFilledStyle);
        createFormulaCell(row, 3, HTC_PR_FORMULA, numberBoldFilledStyle);
        createFormulaCell(row, 4, HTC_NEG_FORMULA, numberBoldFilledStyle);
        createFormulaCell(row, 5, HTC_NET_FORMULA, numberBoldFilledStyle);

        row = sheet.createRow(7);
        createCell(row, 2, GOVIES_DM_CELL, titleLeftBoldStyle);
        createFormulaCell(row, 3, GOV_DM_PR_FORMULA, numberBoldStyle);
        createFormulaCell(row, 4, GOV_DM_NEG_FORMULA, numberBoldStyle);
        createFormulaCell(row, 5, GOV_DM_NET_FORMULA, numberBoldStyle);

        row = sheet.createRow(8);
        createCell(row, 2, DI_CUI_ORD_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPrOrd(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNrOrd(), numberItalicStyle);
        createFormulaCell(row, 5, GOV_DM_ORD_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(9);
        createCell(row, 2, DI_CUI_EC_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPrEc(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNrEc(), numberItalicStyle);
        createFormulaCell(row, 5, GOV_DM_EC_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(10);
        createCell(row, 2, DI_CUI_GRM_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPrGrm(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNrGrm(), numberItalicStyle);
        createFormulaCell(row, 5, GOV_DM_GRM_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(11);
        createCell(row, 2, GOVIES_EM_CELL, titleLeftBoldStyle);
        createFormulaCell(row, 3, GOV_EM_PR_FORMULA, numberBoldStyle);
        createFormulaCell(row, 4, GOV_EM_NEG_FORMULA, numberBoldStyle);
        createFormulaCell(row, 5, GOV_EM_NET_FORMULA, numberBoldStyle);

        row = sheet.createRow(12);
        createCell(row, 2, DI_CUI_ORD_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesemPrOrd(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesemNrOrd(), numberItalicStyle);
        createFormulaCell(row, 5, GOV_EM_ORD_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(13);
        createCell(row, 2, DI_CUI_GRM_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesemPrGrm(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesemNrGrm(), numberItalicStyle);
        createFormulaCell(row, 5, GOV_EM_GRM_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(14);
        createCell(row, 2, DI_CUI_EC_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getGoviesemPrEc(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getGoviesemNrEc(), numberItalicStyle);
        createFormulaCell(row, 5, GOV_EM_EC_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(15);
        createCell(row, 2, CORP_FIN_ABS_CELL, titleLeftBoldStyle);
        createFormulaCell(row, 3, CORP_FIN_ABS_PR_FORMULA, numberBoldStyle);
        createFormulaCell(row, 4, CORP_FIN_ABS_NEG_FORMULA, numberBoldStyle);
        createFormulaCell(row, 5, CORP_FIN_ABS_NET_FORMULA, numberBoldStyle);

        row = sheet.createRow(16);
        createCell(row, 2, DI_CUI_ORD_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getCorpPrOrd(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getCorpNrOrd(), numberItalicStyle);
        createFormulaCell(row, 5, CORP_FIN_ABS_ORD_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(17);
        createCell(row, 2, DI_CUI_EC_CELL, titleLeftItalicStyle);
        createCell(row, 3, riservaNetta.getCorpPrEc(), numberItalicStyle);
        createCell(row, 4, riservaNetta.getCorpNrEc(), numberItalicStyle);
        createFormulaCell(row, 5, CORP_FIN_ABS_EC_NET_FORMULA, numberItalicStyle);

        row = sheet.createRow(19);
        createCell(row, 2, TOTALE_HTCS_CELL, titleLeftFilledStyle);
        createFormulaCell(row, 3, HTCS_PR_FORMULA, numberBoldFilledStyle);
        createFormulaCell(row, 4, HTCS_NEG_FORMULA, numberBoldFilledStyle);
        createFormulaCell(row, 5, HTCS_NET_FORMULA, numberBoldFilledStyle);

        row = sheet.createRow(20);
        createCell(row, 2, GOVIES_DM_CELL, titleLeftStyle);
        createCell(row, 3, riservaNetta.getGoviesdmPr(), numberStyle);
        createCell(row, 4, riservaNetta.getGoviesdmNr(), numberStyle);
        createFormulaCell(row, 5, HTCS_GOV_DM_NET_FORMULA, numberStyle);

        row = sheet.createRow(21);
        createCell(row, 2, GOVIES_EM_CELL, titleLeftStyle);
        createCell(row, 3, riservaNetta.getGoviesemPr(), numberStyle);
        createCell(row, 4, riservaNetta.getGoviesemNr(), numberStyle);
        createFormulaCell(row, 5, HTCS_GOV_EM_NET_FORMULA, numberStyle);

        row = sheet.createRow(22);
        createCell(row, 2, CORP_FIN_ABS_CELL, titleLeftStyle);
        createCell(row, 3, riservaNetta.getCorpPr(), numberStyle);
        createCell(row, 4, riservaNetta.getCorpNr(), numberStyle);
        createFormulaCell(row, 5, HTCS_CORP_FIN_ABS_NET_FORMULA, numberStyle);

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
