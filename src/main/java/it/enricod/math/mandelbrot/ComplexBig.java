package it.enricod.math.mandelbrot;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComplexBig {

	private BigDecimal re;
	private BigDecimal im;

	static ComplexBig zero = new ComplexBig(BigDecimal.ZERO, BigDecimal.ZERO);
	static int scale = 16;

	public ComplexBig(BigDecimal re, BigDecimal im) {
		this.re = re;
		this.im = im;
	}

	public ComplexBig add(ComplexBig c) {
		return new ComplexBig(re.add(c.re).setScale(scale, RoundingMode.CEILING),
				im.add(c.im).setScale(scale, RoundingMode.CEILING));
	}
	
	public BigDecimal re() {
		return re;
	}
	
	public BigDecimal im() {
		return im;
	}

	/**
	 * al quadrato
	 */
	public ComplexBig pow2() {
		BigDecimal two = new BigDecimal(2);
		// x ^2 - y^2, 2xy
		return new ComplexBig(re.multiply(re).subtract(im.multiply(im)).setScale(scale, RoundingMode.CEILING),
				two.multiply(re).multiply(im).setScale(scale, RoundingMode.CEILING));
	}

	public ComplexBig subtract(ComplexBig c) {
		return new ComplexBig(re.subtract(c.re), im.subtract(c.im));
	}

	public Double length() {
		return Math.pow(re.doubleValue(), 2) + Math.pow(im.doubleValue(), 2);
	}

	public String toString() {
		return String.format("%s, %s", re, im);
	}

}