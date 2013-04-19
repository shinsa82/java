package com.ibm.ar.math;

import java.util.Arrays;

public class StatUtils {
	public static double quantile(double[] array, double p) {
		// System.out.println("original = " + ArrayUtils.toString(array));
		if (array.length == 0) {
			return 0.0;
		}
		int len = array.length;
		Arrays.sort(array);
		// System.out.println("sorted = " + ArrayUtils.toString(array));

		double[] quantiles = new double[len];
		for (int i = 0; i < len; i++) {
			quantiles[i] = (i + 0.5) / len;
		}
		// System.out.println("quantiles = " + ArrayUtils.toString(quantiles));
		if (p <= quantiles[0]) {
			return array[0];
		}
		if (p >= quantiles[len - 1]) {
			return array[len - 1];
		}
		for (int i = 0; i < len; i++) {
			if (p == quantiles[i]) {
				return array[i];
			}
		}
		for (int i = 0; i < len - 1; i++) {
			if (quantiles[i] < p && p < quantiles[i + 1]) {
				return array[i] + (p - quantiles[i])
						/ (quantiles[i + 1] - quantiles[i])
						* (array[i + 1] - array[i]);
			}
		}
		// would not be reached here
		return 0.0;
	}

	public static double stdev(double[] array, Double mean) {
		if (array.length == 0) {
			return 0.0;
		}
		if (mean == null) {
			mean = mean(array);
		}
		double sqSum = 0;
		for (int i = 0; i < array.length; i++) {
			double d = array[i] - mean;
			sqSum += (d * d);
		}
		return Math.sqrt(sqSum / array.length);
	}

	public static double stdev(double[] array) {
		return stdev(array, null);
	}

	public static double mean(double[] array) {
		if (array.length == 0) {
			return 0;
		}
		double sum = 0.0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum / array.length;
	}
}
