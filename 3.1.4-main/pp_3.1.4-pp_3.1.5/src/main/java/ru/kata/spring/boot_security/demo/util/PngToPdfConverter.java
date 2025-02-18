package ru.kata.spring.boot_security.demo.util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Component
public class PngToPdfConverter {
	
	// Метод для преобразования PNG данных в PDF
	public static byte[] convertPngToPdf(byte[] pngData) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		PdfWriter writer = new PdfWriter(byteArrayOutputStream);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
		
		Image image = new Image(ImageDataFactory.create(pngData));
		document.add(image);
		
		document.close();
		
		return byteArrayOutputStream.toByteArray();
	}
}
