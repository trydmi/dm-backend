CREATE TABLE riserva_netta (
    id BIGSERIAL primary key,
    date DATE NOT NULL,

    goviesdm_pr_ord DECIMAL NOT NULL,
    goviesdm_pr_ec DECIMAL NOT NULL,
    goviesdm_pr_grm DECIMAL NOT NULL,
    goviesem_pr_ord DECIMAL NOT NULL,
    goviesem_pr_grm DECIMAL NOT NULL,
    goviesem_pr_ec DECIMAL NOT NULL,
    corp_pr_ord DECIMAL NOT NULL,
    corp_pr_ec DECIMAL NOT NULL,

    goviesdm_nr_ord DECIMAL NOT NULL,
    goviesdm_nr_ec DECIMAL NOT NULL,
    goviesdm_nr_grm DECIMAL NOT NULL,
    goviesem_nr_ord DECIMAL NOT NULL,
    goviesem_nr_grm DECIMAL NOT NULL,
    goviesem_nr_ec DECIMAL NOT NULL,
    corp_nr_ord DECIMAL NOT NULL,
    corp_nr_ec DECIMAL NOT NULL,

    goviesdm_pr DECIMAL NOT NULL,
    goviesem_pr DECIMAL NOT NULL,
    corp_pr DECIMAL NOT NULL,

    goviesdm_nr DECIMAL NOT NULL,
    goviesem_nr DECIMAL NOT NULL,
    corp_nr DECIMAL NOT NULL
);

INSERT INTO riserva_netta (date, goviesdm_pr_ord, goviesdm_pr_ec, goviesdm_pr_grm, goviesem_pr_ord,
                           goviesem_pr_grm, goviesem_pr_ec, corp_pr_ord, corp_pr_ec, goviesdm_nr_ord,
                           goviesdm_nr_ec, goviesdm_nr_grm, goviesem_nr_ord, goviesem_nr_grm, goviesem_nr_ec,
                           corp_nr_ord, corp_nr_ec, goviesdm_pr, goviesem_pr, corp_pr, goviesdm_nr, goviesem_nr, corp_nr)
VALUES ('2021-11-15', 304609335.171357, 369920009.052047, 970402249.368935, 32953599.7077608, 1540209.68389461, 114292.430850953,
        140119996.016792, 176046422.339628, -184243649.72392, -134636873.816962, -2237971208.40373, -10349001.8299567, -3845058.83760332,
        -451068.327034385, -4336491.60376145, -35390872.3493566, 11662105.8532123, 9099899.33817266,
        23794079.3977982, -1003694935.96314, -123957219.422296, -17698867.9185652);