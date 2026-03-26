alter table DST_CREDENCIAL DISABLE constraint FK_CREDENCIAL_PERSONA;

declare
    v_usuario number;
begin
    select id_usuario
    into v_usuario
    from DST_USUARIO u,
         dst_persona p
    where u.id_persona = p.ID_PERSONA
      and p.IDENTIFICACION = '901084310';
    delete from dst_carga_instancia where id_carga in (select id_carga from dst_carga where id_usuario = v_usuario);
    delete from dst_archivo_adjunto where id_carga in (select id_carga from dst_carga where id_usuario = v_usuario);
    delete from dst_nota  where id_carga in (select id_carga from dst_carga where id_usuario = v_usuario);
    delete from dst_tablaoperacion where id_carga in (select id_carga from dst_carga where id_usuario = v_usuario);
    delete from dst_formatodato where ID_ELEMENTOCARGA in (select ID_ELEMENTOCARGA from dst_elementocarga where id_carga in (select id_carga from dst_carga where id_usuario = v_usuario));
    delete from dst_elementocarga where id_carga in (select id_carga from dst_carga where id_usuario = v_usuario);
    delete from dst_carga where id_usuario = v_usuario;

    

    delete DST_PREPARADOR_TIPO_PROCESO where ID_PREPARADOR in  (select ID_PREPARADOR from DST_PREPARADOR where DST_PREPARADOR.ID_USUARIO= v_usuario);
    delete DST_PROCESO_PREPADADOR where ID_PREPARADOR in  (select ID_PREPARADOR from DST_PREPARADOR where DST_PREPARADOR.ID_USUARIO= v_usuario);
    DELETE DST_PREPARADOR where id_usuario = v_usuario;
    DELETE DST_REVISOR_TIPO_PROCESO
    where ID_REVISOR in (select id_revisor from DST_REVISOR where id_usuario = v_usuario);
    delete DST_REVISION_REVISOR where ID_REVISOR in (select id_revisor from DST_REVISOR where id_usuario = v_usuario);
    delete DST_REVISION where ID_PROCESO in (select ID_PROCESO from DST_PROCESO where ID_USUARIO = v_usuario);
    DELETE DST_REVISOR where id_usuario = v_usuario;

    delete DST_PROCESO_LIBERADOR where ID_LIBERADOR in (select ID_LIBERADOR from DST_LIBERADOR where id_usuario = v_usuario);
    DELETE DST_LIBERADOR_TIPO_PROCESO
    where ID_LIBERADOR in (select ID_LIBERADOR from DST_LIBERADOR where id_usuario = v_usuario);
    DELETE DST_LIBERADOR where id_usuario = v_usuario;

    DELETE DST_ADMINCLIENTE where id_usuario = v_usuario;
    delete DST_PERSONA where ID_PERSONA in (select ID_PERSONA from DST_CREDENCIAL where ID_USUARIO = v_usuario);
    DELETE DST_CREDENCIAL
    where id_usuario = v_usuario;
    DELETE DST_IPPUBLICA where id_usuario = v_usuario;
    delete DST_USUARIO_NEGOCIO where ID_USUARIO = v_usuario;
    DELETE DST_USUARIO where id_usuario = v_usuario;

end;
/
alter table DST_CREDENCIAL enable constraint FK_CREDENCIAL_PERSONA;
delete DST_PERSONA where identificacion = '1032385845';

commit;