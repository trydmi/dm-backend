package com.luxoft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "riserva_netta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiservaNetta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "goviesdm_pr_ord")
    private Double goviesdmPrOrd;

    @Column(name = "goviesdm_pr_ec")
    private Double goviesdmPrEc;

    @Column(name = "goviesdm_pr_grm")
    private Double goviesdmPrGrm;

    @Column(name = "goviesem_pr_ord")
    private Double goviesemPrOrd;

    @Column(name = "goviesem_pr_grm")
    private Double goviesemPrGrm;

    @Column(name = "goviesem_pr_ec")
    private Double goviesemPrEc;

    @Column(name = "corp_pr_ord")
    private Double corpPrOrd;

    @Column(name = "corp_pr_ec")
    private Double corpPrEc;

    @Column(name = "goviesdm_nr_ord")
    private Double goviesdmNrOrd;

    @Column(name = "goviesdm_nr_ec")
    private Double goviesdmNrEc;

    @Column(name = "goviesdm_nr_grm")
    private Double goviesdmNrGrm;

    @Column(name = "goviesem_nr_ord")
    private Double goviesemNrOrd;

    @Column(name = "goviesem_nr_grm")
    private Double goviesemNrGrm;

    @Column(name = "goviesem_nr_ec")
    private Double goviesemNrEc;

    @Column(name = "corp_nr_ord")
    private Double corpNrOrd;

    @Column(name = "corp_nr_ec")
    private Double corpNrEc;

    @Column(name = "goviesdm_pr")
    private Double goviesdmPr;

    @Column(name = "goviesem_pr")
    private Double goviesemPr;

    @Column(name = "corp_pr")
    private Double corpPr;

    @Column(name = "goviesdm_nr")
    private Double goviesdmNr;

    @Column(name = "goviesem_nr")
    private Double goviesemNr;

    @Column(name = "corp_nr")
    private Double corpNr;
}
