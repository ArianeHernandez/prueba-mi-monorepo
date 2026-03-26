declare
    v_id number := 50583;
    v_sql varchar2(2000);
begin 
    
    v_sql := 'DROP TABLE DST_CREADATOS.T' || v_id;
    SYS.dbms_output.put_line(v_sql);
    execute immediate (v_sql);
    
    
    v_sql := 'delete from dst_campo where id_estructura = ' || v_id;
    SYS.dbms_output.put_line(v_sql);
    execute immediate (v_sql);
    
    v_sql := 'delete from dst_estructura where id_estructura = ' || v_id;
    SYS.dbms_output.put_line(v_sql);
    execute immediate (v_sql);

end;