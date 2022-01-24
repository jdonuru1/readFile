package com.apple.ist.csqe.test.yourmodulename;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo {
	public static void main(String[] arg) {
		String formula = "11.3 + 1.35x";
		Demo d = new Demo();
		String tmpdir = System.getProperty("java.io.tmpdir") + "/input.csv";
		d.compute(formula, tmpdir);
	}

	public void compute(String formula, String fileLocation) {

		String line = "";
		String splitBy = ",";
		String op = "";
		double N = 0;
		double c = 0;
		boolean isValidFormula = false;

		try {
			op = formula.split(" ")[1];
			N = Double.parseDouble((formula.split(" ")[2]).replace("x", ""));
			c = Double.parseDouble(formula.split(" ")[0]);
			isValidFormula = true;
		} catch (NumberFormatException ex) {
			System.out.println("Please correct formula");
		}
		int count = 0;
		if (isValidFormula) {
			try {

				BufferedReader br = new BufferedReader(new FileReader(fileLocation));

				double deltaMean = 0;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(splitBy);
					try {
						int x = Integer.parseInt(data[0]);
						int y = Integer.parseInt(data[1]);
						count++;

						double yNegate = 1;

						if (op.equals("+")) {
							yNegate = c + (N * x);
						} else {
							yNegate = c - (N * x);
						}
						double ds = Math.pow((yNegate - y), 2);
						deltaMean += ds;

					} catch (NumberFormatException ex) {
						System.out.println("Please correct data type in input file");
					}
				}
				double m = deltaMean / count;
				;

				System.out.println("Delta Mean " + m);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}

class Data {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
