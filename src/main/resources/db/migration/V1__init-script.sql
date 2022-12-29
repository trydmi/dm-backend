CREATE TABLE riserva_netta
(
    id              BIGSERIAL primary key,
    date            DATE    NOT NULL,

    goviesdm_pr_ord DECIMAL NOT NULL,
    goviesdm_pr_ec  DECIMAL NOT NULL,
    goviesdm_pr_grm DECIMAL NOT NULL,
    goviesem_pr_ord DECIMAL NOT NULL,
    goviesem_pr_grm DECIMAL NOT NULL,
    goviesem_pr_ec  DECIMAL NOT NULL,
    corp_pr_ord     DECIMAL NOT NULL,
    corp_pr_ec      DECIMAL NOT NULL,

    goviesdm_nr_ord DECIMAL NOT NULL,
    goviesdm_nr_ec  DECIMAL NOT NULL,
    goviesdm_nr_grm DECIMAL NOT NULL,
    goviesem_nr_ord DECIMAL NOT NULL,
    goviesem_nr_grm DECIMAL NOT NULL,
    goviesem_nr_ec  DECIMAL NOT NULL,
    corp_nr_ord     DECIMAL NOT NULL,
    corp_nr_ec      DECIMAL NOT NULL,

    goviesdm_pr     DECIMAL NOT NULL,
    goviesem_pr     DECIMAL NOT NULL,
    corp_pr         DECIMAL NOT NULL,

    goviesdm_nr     DECIMAL NOT NULL,
    goviesem_nr     DECIMAL NOT NULL,
    corp_nr         DECIMAL NOT NULL
);