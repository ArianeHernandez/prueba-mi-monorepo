update dst_carga set estado = 'S'
where id_carga = 110151;

delete from dst_carga_instancia
where id_carga = 110151;

update dst_archivo_adjunto
set radicado = null
where id_carga = 110151;


update btg_creadatos.T50502 set C51331 = NULL, C51330 = NULL where idcarga = 110151 ;