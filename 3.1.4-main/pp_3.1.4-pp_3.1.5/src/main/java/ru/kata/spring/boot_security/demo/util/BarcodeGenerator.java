package ru.kata.spring.boot_security.demo.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class BarcodeGenerator {
	
	// Метод для генерации PNG изображения штрих-кода для каждого поля
	public static byte[] generateBarcodePng(List<String> fieldsToPrint) throws WriterException, IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		for (String field : fieldsToPrint) {
			// Генерация штрих-кода (Code 128)
			Code128Writer barcodeWriter = new Code128Writer();
			BitMatrix bitMatrix = barcodeWriter.encode(field, BarcodeFormat.CODE_128, 580, 400);
			
			// Преобразуем BitMatrix в BufferedImage
			BufferedImage barcodeImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < bitMatrix.getHeight(); i++) {
				for (int j = 0; j < bitMatrix.getWidth(); j++) {
					barcodeImage.setRGB(j, i, (bitMatrix.get(j, i)) ? 0x000000 : 0xFFFFFF);
				}
			}
			
			// Сохранение изображения штрих-кода в поток
			ImageIO.write(barcodeImage, "PNG", byteArrayOutputStream);
		}
		
		return byteArrayOutputStream.toByteArray();
	}
}
