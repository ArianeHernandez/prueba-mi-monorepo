package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class PdfConverterRequest {
		private Origen origen;
		private List<Imagen> imagenes;
		

		public PdfConverterRequest(Origen origen, List<Imagen> imagenes) {
			super();
			this.origen = origen;
			this.imagenes = imagenes;
		}
		public Origen getOrigen() {
			return origen;
		}
		public void setOrigen(Origen origen) {
			this.origen = origen;
		}
		public List<Imagen> getImagenes() {
			return imagenes;
		}
		public void setImagenes(List<Imagen> imagenes) {
			this.imagenes = imagenes;
		}
}
