package com.luxoft.mapper;

import com.luxoft.dto.ExportDataDto;
import com.luxoft.model.RiservaNetta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMapperTest {
    private RiservaNetta riservaNetta;
    private MyMapper mapper;

    private static final Double DELTA = 0.000001;
    private static final Double GOVIESDM_PR = 37.502;
    private static final Double GOVIESEM_PR = 37.502;
    private static final Double CORP_PR = 4.97;
    private static final Double GOVIESDM_NEG = 37.502;
    private static final Double GOVIESEM_NEG = 167.5194;
    private static final Double CORP_NEG = 34660.3652;

    private static final Double HTC_TOTALE_PR = GOVIESDM_PR + GOVIESEM_PR + CORP_PR;
    private static final Double HTC_TOTALE_NEG = GOVIESDM_NEG + GOVIESEM_NEG + CORP_NEG;
    private static final Double HTCS_TOTALE_PR = GOVIESDM_PR + GOVIESEM_PR + CORP_PR;
    private static final Double HTCS_TOTALE_NEG = GOVIESDM_NEG + GOVIESEM_NEG + CORP_NEG;

    private static final Double GOVIESDM_ORD_NET = 34.882;
    private static final Double GOVIESDM_EC_NET = 4.97;
    private static final Double GOVIESDM_GRM_NET = 35.152;
    private static final Double GOVIESEM_ORD_NET = 34.882;
    private static final Double GOVIESEM_EC_NET = 34.882;
    private static final Double CORP_ORD_NET = 34660.0952;
    private static final Double CORP_EC_NET = 5.24;
    private static final Double GOVIESDM_NET = GOVIESDM_ORD_NET + GOVIESDM_EC_NET + GOVIESDM_GRM_NET;
    private static final Double CORP_NET = CORP_ORD_NET + CORP_EC_NET;
    private static final Double HTCS_GOVIESDM_NET = GOVIESDM_PR + GOVIESDM_NEG;//
    private static final Double HTCS_GOVIESEM_NET = GOVIESEM_PR + GOVIESEM_NEG;
    private static final Double HTCS_CORP_NET = CORP_PR + CORP_NEG;
    private static final Double HTCS_TOTALE_NET = HTCS_GOVIESDM_NET + HTCS_GOVIESEM_NET + HTCS_CORP_NET;

    @BeforeEach
    void init() {
        riservaNetta = RiservaNetta.builder()
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
        mapper = new MyMapper();
    }

    @Test
    void riservaNettaToExportDataDto() {
        ExportDataDto exportDataDto = mapper.riservaNettaToExportDataDto(riservaNetta);
        assertEquals(riservaNetta.getDate(), exportDataDto.date());
        assertEquals(riservaNetta.getGoviesdmPrOrd(), exportDataDto.goviesdmPrOrd());
        assertEquals(riservaNetta.getGoviesdmPrEc(), exportDataDto.goviesdmPrEc());
        assertEquals(riservaNetta.getGoviesdmPrGrm(), exportDataDto.goviesdmPrGrm());
        assertEquals(riservaNetta.getGoviesemPrOrd(), exportDataDto.goviesemPrOrd());
        assertEquals(riservaNetta.getGoviesemPrGrm(), exportDataDto.goviesemPrGrm());
        assertEquals(riservaNetta.getGoviesemPrEc(), exportDataDto.goviesemPrEc());
        assertEquals(riservaNetta.getCorpPrOrd(), exportDataDto.corpPrOrd());
        assertEquals(riservaNetta.getCorpPrEc(), exportDataDto.corpPrEc());
        assertEquals(riservaNetta.getGoviesdmNrOrd(), exportDataDto.goviesdmNegOrd());
        assertEquals(riservaNetta.getGoviesdmNrEc(), exportDataDto.goviesdmNegEc());
        assertEquals(riservaNetta.getGoviesdmNrGrm(), exportDataDto.goviesdmNegGrm());
        assertEquals(riservaNetta.getGoviesemNrOrd(), exportDataDto.goviesemNegOrd());
        assertEquals(riservaNetta.getGoviesemNrGrm(), exportDataDto.goviesemNegGrm());
        assertEquals(riservaNetta.getGoviesemNrEc(), exportDataDto.goviesemNegEc());
        assertEquals(riservaNetta.getCorpNrOrd(), exportDataDto.corpNegOrd());
        assertEquals(riservaNetta.getCorpNrEc(), exportDataDto.corpNegEc());
        assertEquals(GOVIESDM_PR, exportDataDto.goviesdmPr(), DELTA);
        assertEquals(GOVIESEM_PR, exportDataDto.goviesemPr(), DELTA);
        assertEquals(CORP_PR, exportDataDto.corpPr(), DELTA);
        assertEquals(GOVIESDM_NEG, exportDataDto.goviesdmNeg(), DELTA);
        assertEquals(GOVIESEM_NEG, exportDataDto.goviesemNeg(), DELTA);
        assertEquals(CORP_NEG, exportDataDto.corpNeg(), DELTA);
        assertEquals(GOVIESDM_ORD_NET, exportDataDto.goviesdmOrdNet(), DELTA);
        assertEquals(GOVIESDM_EC_NET, exportDataDto.goviesdmEcNet(), DELTA);
        assertEquals(GOVIESDM_GRM_NET, exportDataDto.goviesdmGrmNet(), DELTA);
        assertEquals(GOVIESEM_ORD_NET, exportDataDto.goviesemOrdNet(), DELTA);
        assertEquals(GOVIESEM_EC_NET, exportDataDto.goviesemEcNet(), DELTA);
        assertEquals(CORP_ORD_NET, exportDataDto.corpOrdNet(), DELTA);
        assertEquals(CORP_EC_NET, exportDataDto.corpEcNet(), DELTA);
        assertEquals(GOVIESDM_NET, exportDataDto.goviesdmNet(), DELTA);
        assertEquals(CORP_NET, exportDataDto.corpNet(), DELTA);
        assertEquals(HTC_TOTALE_PR, exportDataDto.htcTotalePr(), DELTA);
        assertEquals(HTC_TOTALE_NEG, exportDataDto.htcTotaleNeg(), DELTA);
        assertEquals(HTCS_TOTALE_PR, exportDataDto.htcTotalePr(), DELTA);
        assertEquals(HTCS_TOTALE_NEG, exportDataDto.htcTotaleNeg(), DELTA);
        assertEquals(HTCS_TOTALE_NET, exportDataDto.htcTotaleNet(), DELTA);
    }
}