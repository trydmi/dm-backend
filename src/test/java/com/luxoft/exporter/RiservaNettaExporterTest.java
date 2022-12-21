package com.luxoft.exporter;

import com.luxoft.model.RiservaNetta;
import lombok.Cleanup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiservaNettaExporterTest {
    private RiservaNettaExporter riservaNettaExporter;
    private RiservaNetta riservaNetta;
    private String sheetName;
    private XSSFSheet sheet;

    @BeforeEach
    void setUp() {
        this.riservaNetta = RiservaNetta.builder()
                .date(LocalDate.of(2022, 8, 16))
                .goviesdmPrOrd(2.35)
                .goviesdmPrEc(2.62)
                .goviesdmPrGrm(32.532)
                .goviesemPrOrd(2.35)
                .goviesemPrGrm(2.62)
                .goviesemPrEc(32.532)
                .corpPrOrd(2.35)
                .corpPrEc(2.62)
                .goviesdmNrOrd(32.532)
                .goviesdmNrEc(2.35)
                .goviesdmNrGrm(2.62)
                .goviesemNrOrd(32.532)
                .goviesemNrGrm(132.6374)
                .goviesemNrEc(2.35)
                .corpNrOrd(34657.7452)
                .corpNrEc(2.62)
                .goviesdmPr(2356.745)
                .goviesemPr(2341356.7456)
                .corpPr(2341356.7456)
                .goviesdmNr(243.325678)
                .goviesemNr(2341356.7456)
                .corpNr(2341356.7456)
                .build();
        riservaNettaExporter = new RiservaNettaExporter(riservaNetta);
        sheetName = "Riserva HTC-HTCS";
    }

    @Test
    void shouldBeSuccessfulWhenCorrectData() throws IOException {
        @Cleanup XSSFWorkbook workbook = riservaNettaExporter.export();
        sheet = workbook.getSheet(sheetName);
        assertEquals(riservaNetta.getGoviesdmPrOrd(), getValue(8, 3));
        assertEquals(riservaNetta.getGoviesdmPrEc(), getValue(9, 3));
        assertEquals(riservaNetta.getGoviesdmPrGrm(), getValue(10, 3));
        assertEquals(riservaNetta.getGoviesemPrOrd(), getValue(12, 3));
        assertEquals(riservaNetta.getGoviesemPrGrm(), getValue(13, 3));
        assertEquals(riservaNetta.getGoviesemPrEc(), getValue(14, 3));
        assertEquals(riservaNetta.getCorpPrOrd(), getValue(16, 3));
        assertEquals(riservaNetta.getCorpPrEc(), getValue(17, 3));
        assertEquals(riservaNetta.getGoviesdmNrOrd(), getValue(8, 4));
        assertEquals(riservaNetta.getGoviesdmNrEc(), getValue(9, 4));
        assertEquals(riservaNetta.getGoviesdmNrGrm(), getValue(10, 4));
        assertEquals(riservaNetta.getGoviesemNrOrd(), getValue(12, 4));
        assertEquals(riservaNetta.getGoviesemNrGrm(), getValue(13, 4));
        assertEquals(riservaNetta.getGoviesemNrEc(), getValue(14, 4));
        assertEquals(riservaNetta.getCorpNrOrd(), getValue(16, 4));
        assertEquals(riservaNetta.getCorpNrEc(), getValue(17, 4));
        assertEquals(riservaNetta.getGoviesdmPr(), getValue(20, 3));
        assertEquals(riservaNetta.getGoviesemPr(), getValue(21, 3));
        assertEquals(riservaNetta.getCorpPr(), getValue(22, 3));
        assertEquals(riservaNetta.getGoviesdmNr(), getValue(20, 4));
        assertEquals(riservaNetta.getGoviesemNr(), getValue(21, 4));
        assertEquals(riservaNetta.getCorpNr(), getValue(22, 4));
        assertEquals(getValue(8, 3) + getValue(9, 3) + getValue(10, 3), getValue(7, 3));
        assertEquals(getValue(12, 3) + getValue(13, 3) + getValue(14, 3), getValue(11, 3));
        assertEquals(getValue(16, 3) + getValue(17, 3), getValue(15, 3));
        assertEquals(getValue(8, 4) + getValue(9, 4) + getValue(10, 4), getValue(7, 4));
        assertEquals(getValue(12, 4) + getValue(13, 4) + getValue(14, 4), getValue(11, 4));
        assertEquals(getValue(16, 4) + getValue(17, 4), getValue(15, 4));
        assertEquals(getValue(7, 3) + getValue(11, 3) + getValue(15, 3), getValue(6, 3));
        assertEquals(getValue(7, 4) + getValue(11, 4) + getValue(15, 4), getValue(6, 4));
        assertEquals(getValue(20, 3) + getValue(21, 3) + getValue(22, 3), getValue(19, 3));
        assertEquals(getValue(20, 4) + getValue(21, 4) + getValue(22, 4), getValue(19, 4));
        assertEquals(getValue(8, 5) + getValue(9, 5) + getValue(10, 5), getValue(7, 5));
        assertEquals(getValue(12, 5) + getValue(13, 5) + getValue(14, 5), getValue(11, 5));
        assertEquals(getValue(16, 5) + getValue(17, 5), getValue(15, 5));
        assertEquals(getValue(7, 5) + getValue(11, 5) + getValue(15, 5), getValue(6, 5));
        assertEquals(getValue(20, 5) + getValue(21, 5) + getValue(22, 5), getValue(19, 5));
    }

    private Double getValue(int row, int column) {
        return sheet.getRow(row).getCell(column).getNumericCellValue();
    }
}