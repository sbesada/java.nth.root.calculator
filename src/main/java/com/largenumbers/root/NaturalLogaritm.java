package com.largenumbers.root;

import java.math.BigDecimal;
import java.math.MathContext;


/**
 * The Class NaturalLogaritm.
 */
public class NaturalLogaritm {

	/** The log 10. */
	private static double LOG_10 = 2.302585092994046;
	
	/** The e^700. */
	private static double E_700 = 1.0142320547350045E304;
	
	/** The e^20. */
	private static double E_20 = 4.851651954097903E8;

	/**
	 * Ln.
	 *
	 * @param number the number
	 * @param mc the mc
	 * @return the big decimal
	 */
	public static BigDecimal ln(final BigDecimal number, final MathContext mc) {

		final int exponent = BigDecimalUtil.exponent(number);
		final BigDecimal mantissa = BigDecimalUtil.mantissa(number);

		BigDecimal result = new BigDecimal(Math.log(mantissa.doubleValue()));
		if (exponent != 0) {
			result = result.add(BigDecimal.valueOf(exponent).multiply(BigDecimal.valueOf(LOG_10), mc));
		}

		return result;

	}

	/**
	 * Ln double.
	 *
	 * @param number the number
	 * @param mc the mc
	 * @return the double
	 */
	public static double lnDouble(final BigDecimal number, final MathContext mc) {

		final int exponent = BigDecimalUtil.exponent(number);
		final BigDecimal mantissa = BigDecimalUtil.mantissa(number);

		double result = Math.log(mantissa.doubleValue());
		if (exponent != 0) {
			result += exponent * LOG_10;
		}

		return result;

	}

	/**
	 * Exp taylor finite.
	 *
	 * @param number the number
	 * @param order the order
	 * @param mc the mc
	 * @return the big decimal
	 * @throws CalculatorException the calculator exception
	 */
	public static BigDecimal expTaylorFinite(final BigDecimal number, final int order, final MathContext mc)
			throws CalculatorException {
		BigDecimal result = BigDecimal.ZERO;
		for (int i = 0; i < order; i++) {
			BigDecimal aux = number.pow(i, mc);
			final BigDecimal factorial = Factorial.cal(i, mc);
			aux = aux.divide(factorial, mc);
			result = result.add(aux, mc);
			if (aux.doubleValue() < 0.5) {
				break;
			}
		}

		return result;
	}

	/**
	 * Exp tylor infinite.
	 *
	 * @param number the number
	 * @param mc the mc
	 * @return the big decimal
	 * @throws CalculatorException the calculator exception
	 */
	public static BigDecimal expTylorInfinite(final BigDecimal number, final MathContext mc)
			throws CalculatorException {
		BigDecimal result = BigDecimal.ZERO;
		boolean end = false;
		int i = 0;
		do {
			BigDecimal aux = number.pow(i, mc);
			final BigDecimal factorial = Factorial.cal(i, mc);
			aux = aux.divide(factorial, mc);
			if (aux.doubleValue() < 0.5 || i == Integer.MAX_VALUE - 1) {
				end = true;
			}
			i++;
			result = result.add(aux, mc);

		} while (!end);

		return result;
	}

	/**
	 * Exp.
	 *
	 * @param number the number
	 * @param mc the mc
	 * @return the big decimal
	 * @throws CalculatorException the calculator exception
	 */
	public static BigDecimal exp(final BigDecimal number, final MathContext mc) throws CalculatorException {

		BigDecimal result = BigDecimal.ONE;
		BigDecimal aux = number;

		if (number.compareTo(BigDecimal.valueOf(700)) > 0) {
			final long numOfAdditions = numberOfAddition(number, mc);
			for (int i = 0; i < numOfAdditions; i++) {
				result = result.multiply(BigDecimal.valueOf(E_700), mc);
				aux = aux.subtract(BigDecimal.valueOf(700), mc);
			}
			result = result.multiply(BigDecimal.valueOf(Math.exp(aux.doubleValue())), mc);

		} else {

			result = new BigDecimal(Math.exp(number.doubleValue()));
		}

		return result;

	}

	/**
	 * Number of addition.
	 *
	 * @param number the number
	 * @param mc the mc
	 * @return the long
	 */
	private static long numberOfAddition(final BigDecimal number, final MathContext mc) {
		return number.divide(BigDecimal.valueOf(700), mc).longValue();

	}

}
