package it.enricod.math.mandelbrot;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyCanvas {

	int w;
	int h;

	public MyCanvas(int w, int h) {
		super();
		this.w = w;
		this.h = h;
	}

	public Matrix createPointMatrix(ComplexBig a, ComplexBig b) {

		BigDecimal dx = b.re().subtract(a.re()).divide(new BigDecimal(w), ComplexBig.scale, RoundingMode.CEILING);
		BigDecimal dy = new BigDecimal((b.im().doubleValue() - a.im().doubleValue()) / h);// b.im.subtract(a.im).divide(BigDecimal(w),
																							// ComplexBig.scale,
																							// RoundingMode.CEILING)

		Matrix result = new Matrix(h, w);
		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				result.set(row, col,
						new ComplexBig(
								a.re().add(dx.multiply(new BigDecimal(col))).setScale(ComplexBig.scale,
										RoundingMode.CEILING),
								b.im().subtract(dy.multiply(new BigDecimal(row))).setScale(ComplexBig.scale,
										RoundingMode.CEILING)));
			}
		}

		ComplexBig estremoBassoDestra = (result.get(h - 1, w - 1));
		System.out.println(estremoBassoDestra);
		return result;

	}
}