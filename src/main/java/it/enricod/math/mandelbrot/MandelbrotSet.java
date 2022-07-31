package it.enricod.math.mandelbrot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class MandelbrotSet {

	public static int imageHeight = 1080;

	public static int max = 1000;

	public static int[] colors() {
		int[] colors = new int[max];
		for (int i = 0; i < max; i++) {
			colors[i] = Color.HSBtoRGB(i / 256f, 1f, i / (i + 8f));
		}
		return colors;
	}

	ComplexBig mandelNext(ComplexBig z, ComplexBig c) {
		return z.pow2().add(c);
	}

	/**
	 * @param a punto in basso a sinistra
	 * @param b punto in alto a destra
	 */
	public void run2(ComplexBig a, ComplexBig b) {

		int imageWidth = (int) (imageHeight * (b.re().doubleValue() - a.re().doubleValue())
				/ (b.im().doubleValue() - a.im().doubleValue()));
		MyCanvas canvas = new MyCanvas(imageWidth, imageHeight);
		Matrix pointsMatrix = canvas.createPointMatrix(a, b);

		BufferedImage image = new BufferedImage(canvas.w, canvas.h, BufferedImage.TYPE_INT_RGB);
		int black = 0;
		int[] colors = colors();

		for (int row = 0; row < canvas.h; row++) {
			for (int col = 0; col < canvas.w; col++) {

				ComplexBig c = pointsMatrix.get(row, col);

				ComplexBig z = ComplexBig.zero;
				int iteration = 0;
				// println("$iteration: $z")
				while (z.length() < 4.001 && iteration < max) {
					z = mandelNext(z, c);
					// println("\t\t $iteration \t z=$z \t length=${z.length()}" )
					iteration++;
				}

				// println("\t ***** \t row=$row , col=$col, iterazioni: $iteration")
				if (iteration < max)
					image.setRGB(col, row, colors[iteration]);
				else
					image.setRGB(col, row, black);
			}
		}
		try {
			ImageIO.write(image, "png", new File("mandelbrot3.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void runWithDoubles() {
		int imageWidth = 1080;
		MyCanvas canvas = new MyCanvas(imageWidth, imageHeight);
		BufferedImage image = new BufferedImage(canvas.w, canvas.h, BufferedImage.TYPE_INT_RGB);
		int black = 0;
		int[] colors = colors();

		for (int row = 0; row < canvas.h; row++) {
			for (int col = 0; col < canvas.w; col++) {
				double c_re = (col - canvas.w / 2) * 4.0 / canvas.w;
				double c_im = (row - canvas.h / 2) * 4.0 / canvas.w;
				double x = 0.0;
				double y = 0.0;
				int iteration = 0;

				while (x * x + y * y < 4 && iteration < max) {
					double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iteration++;
				}
				if (iteration < max)
					image.setRGB(col, row, colors[iteration]);
				else
					image.setRGB(col, row, black);
			}
		}

		try {
			System.out.println("salvo file");
			ImageIO.write(image, "png", new File("mandelbrot.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}