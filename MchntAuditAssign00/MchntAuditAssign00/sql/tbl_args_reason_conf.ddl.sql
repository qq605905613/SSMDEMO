SET CURRENT SCHEMA ARGS;

CREATE TABLE
    ARGS.TBL_ARGS_REASON_CONF
 ( 
     MCHNT_TP CHARACTER(4) WITH DEFAULT NOT NULL,
     SPEC_DISC_TP CHARACTER(2) WITH DEFAULT NOT NULL,
     SPEC_DISC_LVL CHARACTER(1) WITH DEFAULT NOT NULL,
     REASON_TP CHARACTER(4) WITH DEFAULT NOT NULL,  
     REC_ST CHARACTER(1) WITH DEFAULT NOT NULL,
     COMMENTS VARCHAR(160) WITH DEFAULT NOT NULL,
     REC_CRT_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL,
     REC_UPD_TS TIMESTAMP DEFAULT CURRENT TIMESTAMP NOT NULL, 
     CONSTRAINT IND_MMGM_RT_PK PRIMARY KEY (REASON_TP,MCHNT_TP,SPEC_DISC_TP,SPEC_DISC_LVL)
 );


GRANT ALL ON ARGS.TBL_ARGS_REASON_CONF TO USER OP_MGMAP;
GRANT SELECT ON ARGS.TBL_ARGS_REASON_CONF TO USER OP_MGMMN;
