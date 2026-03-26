package com.osmosyscol.datasuite.webdata.correval.cargueplano.dto;

import java.math.BigDecimal;
import java.util.List;

public class InfoRetiro {

	private Integer num_retiros = 0;
	private BigDecimal valortotal = new BigDecimal(0);
	private String codError = null;
	private List<String> mensajesError = null;

	private Boolean exitoso = false;

	public InfoRetiro(List<Retiro> retiros, Boolean exitoso, String codError, List<String> mesajesError) {

		if (retiros != null) {

			this.exitoso = exitoso;
			this.codError = codError;
			this.setMensajesError(mesajesError);

			num_retiros = retiros.size();

			for (Retiro retiro : retiros) {
				valortotal = valortotal.add(retiro.getValor());
			}

		}

	}

	// --------------------------------------

	public Boolean getExitoso() {
		return exitoso;
	}

	public void setExitoso(Boolean exitoso) {
		this.exitoso = exitoso;
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public Integer getNum_retiros() {
		return num_retiros;
	}

	public void setNum_retiros(Integer num_retiros) {
		this.num_retiros = num_retiros;
	}

	public BigDecimal getValortotal() {
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public List<String> getMensajesError() {
		return mensajesError;
	}

	public void setMensajesError(List<String> mesajesError) {
		this.mensajesError = mesajesError;
	}

}
