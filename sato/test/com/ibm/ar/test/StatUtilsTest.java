package com.ibm.ar.test;

import static com.ibm.ar.math.StatUtils.*;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatUtilsTest {

	@Test
	public void meanTest() {
		RandomData random = new RandomDataImpl();
		final int LEN = 30;
		double[] array = new double[LEN];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextUniform(-100.0, 100.0);
		}
		System.out.println("array = " + ArrayUtils.toString(array));
		System.out.println("mean = " + mean(array));
		System.out.println("std. dev. = " + stdev(array));
		assert (stdev(array) - stdev(array, mean(array)) < 0.001);
	}

	@DataProvider(name = "doubleArray")
	public Object[][] createData() {
		return new Object[][] { { new double[] { 2, 10, 5, 9, 3 } },
				{ new double[] { 52, 96, 27, 20, 33 } },
				{ new double[] { 80, 41, 97, 63, 88, 70, 65, 36, 51, 31 } } };
	}

	@Test(dataProvider = "doubleArray")
	public void qualtileTest(double[] data) {
		System.out.println("data = " + ArrayUtils.toString(data));
		for (int i = 0; i <= 10; i++) {
			double p = i / 10.0;
			System.out.println(String.format("  quantile at p=%f = %f", p,
					quantile(data, p)));
		}
	}
}
