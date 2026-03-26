DECLARE
    v_val  NUMBER;
    v_sec  VARCHAR2(200) := 'FORMATO_CAMPO';
     TYPE tabtype IS REF CURSOR;
    c_val tabtype;
    v_sql varchar2(2000);
BEGIN
    v_sql := 'SELECT MAX(ID_'
                   || v_sec
                   || ') + 1 from DST_' || v_sec;
    SYS.dbms_output.put_line(v_sql);

    OPEN c_val FOR v_sql;

    FETCH c_val INTO v_val;
    CLOSE c_val;

    v_sql := 'DROP SEQUENCE DSS_' || v_sec;
    SYS.dbms_output.put_line(v_sql);
    execute immediate (v_sql);

    v_sql := 'CREATE SEQUENCE  "BTG_DATASUITE"."DSS_'
                      || v_sec
                      || '"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH '
                      || v_val
                      || ' CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL';

    SYS.dbms_output.put_line(v_sql);
    execute immediate(v_sql);
END;
/
