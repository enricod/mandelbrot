package it.enricod.math.mandelbrot;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private List<List<ComplexBig>> values = new ArrayList<>();

	public Matrix(int rows, int cols) {

		for (int r = 0; r < rows; r++) {
			ArrayList<ComplexBig> riga = new ArrayList<ComplexBig>();
			for (int c = 0; c < cols; c++) {
				riga.add(ComplexBig.zero);
			}
			values.add(riga);
		}
	}

	public void set(int row, int col, ComplexBig c) {
		values.get(row).set(col, c);
	}

	public ComplexBig get(int row, int col) {
		return values.get(row).get(col);
	}

}