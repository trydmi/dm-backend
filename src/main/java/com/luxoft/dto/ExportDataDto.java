package com.luxoft.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ExportDataDto(
        LocalDate date,
        Double goviesdmPrOrd,
        Double goviesdmPrEc,
        Double goviesdmPrGrm,
        Double goviesemPrOrd,
        Double goviesemPrGrm,
        Double goviesemPrEc,
        Double corpPrOrd,
        Double corpPrEc,
        Double goviesdmNegOrd,
        Double goviesdmNegEc,
        Double goviesdmNegGrm,
        Double goviesemNegOrd,
        Double goviesemNegGrm,
        Double goviesemNegEc,
        Double corpNegOrd,
        Double corpNegEc,
        Double htcsGoviesdmPr,
        Double htcsGoviesemPr,
        Double htcsCorpPr,
        Double htcsGoviesdmNeg,
        Double htcsGoviesemNeg,
        Double htcsCorpNeg,

        Double htcTotalePr,
        Double htcTotaleNeg,
        Double goviesdmPr,
        Double goviesemPr,
        Double corpPr,
        Double goviesdmNeg,
        Double goviesemNeg,
        Double corpNeg,
        Double htcsTotalePr,
        Double htcsTotaleNeg,
        Double htcTotaleNet,
        Double goviesdmNet,
        Double goviesdmOrdNet,
        Double goviesdmEcNet,
        Double goviesdmGrmNet,
        Double goviesemNet,
        Double goviesemOrdNet,
        Double goviesemEcNet,
        Double goviesemGrmNet,
        Double corpNet,
        Double corpOrdNet,
        Double corpEcNet,
        Double htcsTotaleNet,
        Double htcsGoviesdmNet,
        Double htcsGoviesemNet,
        Double htcsCorpNet
) {
}
