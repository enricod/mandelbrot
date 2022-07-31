package it.enricod.math.mandelbrot;

import java.math.BigDecimal;

public class Main {
	public static void main(String[] args) {
		MandelbrotSet mandelbrotSet = new MandelbrotSet();
		// val a = ComplexBig(BigDecimal(-2.001), BigDecimal(-1.5))
//	    //val b = ComplexBig(BigDecimal(1.2), BigDecimal(1.5))
		//
		ComplexBig a = new ComplexBig(new BigDecimal(-1.5), new BigDecimal(-0.7));
		ComplexBig b = new ComplexBig(new BigDecimal(-0.5), new BigDecimal(0.5));
		mandelbrotSet.run2(a, b);
	}
}
