package com.largenumbers.root;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 *
 * @author Sergio Besada
 *
 */
public class Factorial {

	/**
	 *
	 * cal
	 *
	 * @param m
	 * @param valueZero
	 * @return
	 * @throws CalculatorException
	 */
	public static BigDecimal cal(final int m, final MathContext mc) throws CalculatorException {
		if (m < 0) {
			throw new CalculatorException("the number must be greater than 0");
		}

		BigDecimal result = BigDecimal.ONE;
		if (m == 0) {
			result = BigDecimal.ONE;

		} else {
			for (int i = m; i > 0; i--) {
				result = result.multiply(BigDecimal.valueOf(i), mc);
			}
		}
		return result;
	}

	public static long cal(final int m, final boolean valueZero) throws CalculatorException {
		if (m < 0) {
			throw new CalculatorException("the number must be greater than 0");
		}

		long result = 1;
		if (m == 0) {
			if (valueZero) {
				result = 0;
			} else {
				result = 1;
			}
		} else {
			for (int i = m; i > 0; i--) {
				result *= i;
			}
		}
		return result;
	}

}
