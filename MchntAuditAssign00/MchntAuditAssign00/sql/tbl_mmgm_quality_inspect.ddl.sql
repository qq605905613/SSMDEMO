SET CURRENT SCHEMA MMGM;

CREATE TABLE
    TBL_MMGM_QUALITY_INSPECT
    (
        TASK_ID CHARACTER(32) WITH DEFAULT NOT NULL,
        MCHNT_SRV_TP CHARACTER(2) WITH DEFAULT NOT NULL,
        MCHNT_CD CHARACTER(15) WITH DEFAULT NOT NULL,
        MCHNT_CN_NM VARCHAR(100) WITH DEFAULT NOT NULL,
        ACQ_INS_ID_CD VARCHAR(11) WITH DEFAULT NOT NULL,
        ACPT_INS_ID_CD VARCHAR(11) WITH DEFAULT NOT NULL,
        CUP_BRANCH_INS_ID_CD VARCHAR(11) WITH DEFAULT NOT NULL,
        CONN_MD CHARACTER(1) WITH DEFAULT NOT NULL,
        MCHNT_TP CHARACTER(4) WITH DEFAULT NOT NULL,
        SPEC_DISC_TP CHARACTER(2) WITH DEFAULT NOT NULL,
        SPEC_DISC_LVL CHARACTER(1) WITH DEFAULT NOT NULL,
        IS_WHITE_MCHNT CHARACTER(1) WITH DEFAULT NOT NULL,
        ACQ_COMMIT_AUDIT_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
        AUDIT_ST CHARACTER(1) WITH DEFAULT NOT NULL,
        MCHNT_AUDIT_ID CHARACTER(30) WITH DEFAULT NOT NULL,
        ASSIGN_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
        ASSIGN_USR_CD VARCHAR(32) WITH DEFAULT NOT NULL,
        ASSIGN_GROUP_ID VARCHAR(32) WITH DEFAULT NOT NULL,
        PLAN_DONE_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
        ACTUAL_DONE_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
        TASK_RESULT CHARACTER(1) WITH DEFAULT NOT NULL,
        MCHNT_RATING INTEGER DEFAULT 0 NOT NULL,
        PENDING_MEMO VARCHAR(1024) WITH DEFAULT NOT NULL,
        REJECT_MEMO VARCHAR(1024) WITH DEFAULT NOT NULL,
        IS_RIGHT CHARACTER(1) WITH DEFAULT NOT NULL, 
        INSPECT_MEMO  VARCHAR(1024) WITH DEFAULT NOT NULL,
        INSPECT_USR_CD  VARCHAR(32) WITH DEFAULT NOT NULL,
        REC_ST CHARACTER(1) WITH DEFAULT NOT NULL,
        COMMENTS VARCHAR(160) WITH DEFAULT NOT NULL,
        REC_CRT_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
        REC_UPD_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
        CONSTRAINT IND_MMGM_TA_PK PRIMARY KEY (TASK_ID)
    );

GRANT ALL ON MMGM.TBL_MMGM_QUALITY_INSPECT TO USER OP_MGMAP;
GRANT SELECT ON MMGM.TBL_MMGM_QUALITY_INSPECT TO USER OP_MGMMN;