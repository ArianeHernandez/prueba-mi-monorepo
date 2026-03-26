package com.osmosyscol.datasuite.correval.sobreflex;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

//import com.osmosyscol.commons.log.SimpleLogger;

public class PrintText implements Printable {

	private List<String> textoImprimir;
	private int[] posX;
	private int[] posY;
	private int[] tamLetra;
	private int[] margenes;

	public PrintText() {
		posX = new int[5];
		posY = new int[5];
		tamLetra = new int[5];
		margenes = new int[4];
	}

	public boolean imprimirTexto(List<String> texto) {
		if (texto == null || texto.size() == 0) {
			return false;
		}
		textoImprimir = texto;
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		PageFormat pageFormat = printerJob.defaultPage();
		Paper paper = pageFormat.getPaper();
		paper.setImageableArea(margenes[0], margenes[1], margenes[2], margenes[3]);
		pageFormat.setPaper(paper);

		book.append(this, pageFormat);
		printerJob.setPageable(book);
		try {
			printerJob.print();
			return true;
		} catch (PrinterException exception) {
			System.err.println("Printing error: " + exception);
		}
		return false;
	}

	// Se imprime el texto
	public int print(Graphics g, PageFormat format, int pageIndex) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(format.getImageableX(), format.getImageableY());
		g2d.setPaint(Color.black);
		Point2D.Float pen = new Point2D.Float();

		Font font = new Font("Serif", Font.PLAIN, 12);
		g2d.setFont(font);

		Iterator<String> iterator = textoImprimir.iterator();
		// Se dibuja cada linea
		int i = 0;
		while (iterator.hasNext()) {
			FontMetrics metrics = g.getFontMetrics(font);
			if (posX[i] > 0) {
				pen.x = posX[i];
			}
			if (tamLetra[i] > 0) {
				font = new Font("Serif", Font.PLAIN, tamLetra[i]);
				g2d.setFont(font);
				metrics = g.getFontMetrics(font);
			}
			if (posY[i] > 0) {
				pen.y = posY[i] + metrics.getHeight();
			}
			String string = (String) iterator.next();

			g2d.drawString(string, pen.x, pen.y);

			i++;
		}
		return Printable.PAGE_EXISTS;
	}

	public void setCoordenadasX(String coordenadasX) {
		textoANumeros(coordenadasX, posX);
	}

	public void setCoordenadasY(String coordenadasY) {
		textoANumeros(coordenadasY, posY);

	}

	public void setTamLetras(String tamanos) {
		textoANumeros(tamanos, tamLetra);
	}

	public void setMargenes(String margenes) {
		textoANumeros(margenes, this.margenes);
	}

	public void textoANumeros(String texto, int[] vector) {
		StringTokenizer tokenizer = new StringTokenizer(texto);
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			String string = tokenizer.nextToken().trim();
			if (i < vector.length) {
				try {
					vector[i] = Integer.parseInt(string);
				} catch (Exception e) {
					System.out.println("Error convirtiendo a numero: " + string);
				}
				i++;
			}
		}
	}

}
