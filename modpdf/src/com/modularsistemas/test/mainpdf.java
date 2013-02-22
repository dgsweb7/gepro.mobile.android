package com.modularsistemas.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.modularsistemas.modpdf.A4;
import com.modularsistemas.modpdf.Font;
import com.modularsistemas.modpdf.Image;
import com.modularsistemas.modpdf.JPEGImage;
import com.modularsistemas.modpdf.PDF;
import com.modularsistemas.modpdf.Page;
import com.modularsistemas.modpdf.TextLine;

public class mainpdf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			FileOutputStream fOut = new FileOutputStream("c:\\oapsdasd.pdf");

			PDF pdf = new PDF(fOut);
			Font f1 = new Font(pdf, "Helvetica");
			TextLine text = new TextLine(f1);
			Page page = new Page(pdf, A4.PORTRAIT);
			text.setText("Vai toma no cu");
			text.setPosition(20.0, 20.0);
			text.setUnderline(true);

			Image fuimg = new Image(pdf, new FileInputStream("c:\\fu.jpg"), 1);
			fuimg.setPosition(50, 50);
			fuimg.drawOn(page);
			text.drawOn(page);

			pdf.flush();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
