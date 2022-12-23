package com.luxoft.mapper;

import com.luxoft.dto.ExportDataDto;
import com.luxoft.model.RiservaNetta;
import org.springframework.stereotype.Component;

@Component
public class MyMapper {
    public ExportDataDto riservaNettaToExportDataDto(RiservaNetta byDate) {
        Double goviesdmPr = byDate.getGoviesdmPrOrd() + byDate.getGoviesdmPrEc() + byDate.getGoviesdmPrGrm();
        Double goviesemPr = byDate.getGoviesemPrOrd() + byDate.getGoviesemPrGrm() + byDate.getGoviesemPrEc();
        Double corpPr = byDate.getCorpPrOrd() + byDate.getCorpPrEc();
        Double goviesdmNeg = byDate.getGoviesdmNrOrd() + byDate.getGoviesdmNrEc() + byDate.getGoviesdmNrGrm();
        Double goviesemNeg = byDate.getGoviesemNrOrd() + byDate.getGoviesemNrGrm() + byDate.getGoviesemNrEc();
        Double corpNeg = byDate.getCorpNrOrd() + byDate.getCorpNrEc();
        Double goviesdmOrdNet = byDate.getGoviesdmPrOrd() + byDate.getGoviesdmNrOrd();
        Double goviesdmEcNet = byDate.getGoviesdmPrEc() + byDate.getGoviesdmNrEc();
        Double goviesdmGrmNet = byDate.getGoviesdmPrGrm() + byDate.getGoviesdmNrGrm();
        Double goviesemOrdNet = byDate.getGoviesemPrOrd() + byDate.getGoviesemNrOrd();
        Double goviesemGrmNet = byDate.getGoviesemPrGrm() + byDate.getGoviesemNrGrm();
        Double goviesemEcNet = byDate.getGoviesemPrEc() + byDate.getGoviesemNrEc();
        Double corpOrdNet = byDate.getCorpPrOrd() + byDate.getCorpNrOrd();
        Double corpEcNet = byDate.getCorpPrEc() + byDate.getCorpNrEc();
        Double goviesdmNet = goviesdmOrdNet + goviesdmEcNet + goviesdmGrmNet;
        Double goviesemNet = goviesemOrdNet + goviesemGrmNet + goviesemEcNet;
        Double corpNet = corpOrdNet + corpEcNet;
        Double htcsGoviesdmNet = byDate.getGoviesdmPr() + byDate.getGoviesdmNr();
        Double htcsGoviesemNet = byDate.getGoviesemPr() + byDate.getGoviesemNr();
        Double htcsCorpNet = byDate.getCorpPr() + byDate.getCorpNr();
        return ExportDataDto.builder()
                .date(byDate.getDate())
                .goviesdmPrOrd(byDate.getGoviesdmPrOrd())
                .goviesdmPrEc(byDate.getGoviesdmPrEc())
                .goviesdmPrGrm(byDate.getGoviesdmPrGrm())
                .goviesemPrOrd(byDate.getGoviesemPrOrd())
                .goviesemPrGrm(byDate.getGoviesemPrGrm())
                .goviesemPrEc(byDate.getGoviesemPrEc())
                .corpPrOrd(byDate.getCorpPrOrd())
                .corpPrEc(byDate.getCorpPrEc())
                .goviesdmNegOrd(byDate.getGoviesdmNrOrd())
                .goviesdmNegEc(byDate.getGoviesdmNrEc())
                .goviesdmNegGrm(byDate.getGoviesdmNrGrm())
                .goviesemNegOrd(byDate.getGoviesemNrOrd())
                .goviesemNegGrm(byDate.getGoviesemNrGrm())
                .goviesemNegEc(byDate.getGoviesemNrEc())
                .corpNegOrd(byDate.getCorpNrOrd())
                .corpNegEc(byDate.getCorpNrEc())
                .htcsGoviesdmPr(byDate.getGoviesdmPr())
                .htcsGoviesemPr(byDate.getGoviesemPr())
                .htcsCorpPr(byDate.getCorpPr())
                .htcsGoviesdmNeg(byDate.getGoviesdmNr())
                .htcsGoviesemNeg(byDate.getGoviesemNr())
                .htcsCorpNeg(byDate.getCorpNr())

                .goviesdmPr(goviesdmPr)
                .goviesemPr(goviesemPr)
                .corpPr(corpPr)
                .goviesdmNeg(goviesdmNeg)
                .goviesemNeg(goviesemNeg)
                .corpNeg(corpNeg)

                .htcTotalePr(goviesdmPr + goviesemPr + corpPr)
                .htcTotaleNeg(goviesdmNeg + goviesemNeg + corpNeg)

                .htcsTotalePr(byDate.getGoviesdmPr() + byDate.getGoviesemPr() + byDate.getCorpPr())
                .htcsTotaleNeg(byDate.getGoviesdmNr() + byDate.getGoviesemNr() + byDate.getCorpNr())

                .goviesdmOrdNet(goviesdmOrdNet)
                .goviesdmEcNet(goviesdmEcNet)
                .goviesdmGrmNet(goviesdmGrmNet)
                .goviesemOrdNet(goviesemOrdNet)
                .goviesemGrmNet(goviesemGrmNet)
                .goviesemEcNet(goviesemEcNet)
                .corpOrdNet(corpOrdNet)
                .corpEcNet(corpEcNet)

                .goviesdmNet(goviesdmNet)
                .goviesemNet(goviesemNet)
                .corpNet(corpNet)

                .htcTotaleNet(goviesdmNet + goviesemNet + corpNet)
                .htcsGoviesdmNet(htcsGoviesdmNet)
                .htcsGoviesemNet(htcsGoviesemNet)
                .htcsCorpNet(htcsCorpNet)

                .htcsTotaleNet(htcsGoviesdmNet + htcsGoviesemNet + htcsCorpNet)
                .build();
    }
}
