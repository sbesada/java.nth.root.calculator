package com.largenumbers.root;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

/**
 * The Class Root.
 */
public class Root {

	/**
	 * Nth root logaritm double.
	 *
	 * @param number the number
	 * @param n the n
	 * @param mc the mc
	 * @return the big decimal
	 * @throws CalculatorException the calculator exception
	 */
	public static BigDecimal nthRootLogaritmDouble(final BigDecimal number, final int n, final MathContext mc)
			throws CalculatorException {
		BigDecimal result = null;

		double aux = NaturalLogaritm.lnDouble(number, mc);
		aux = aux * (1.0 / n);

		result = NaturalLogaritm.exp(BigDecimal.valueOf(aux), mc);

		return result;

	}

	/**
	 * Nth root order 3 --> Xk+1=Xk*[((n+1)*r + (n-1)Xk^n)/(n-1)*r + (n+1)*xk^n]
	 *
	 * @param number the number
	 * @param n the n
	 * @param mc the mc
	 * @param x0 the x 0
	 * @return the object
	 */
	public static Object nthRootOrder3(final BigDecimal number, final int n, final MathContext mc,
			final BigDecimal x0) {

		BigDecimal x = x0;
		BigDecimal x1 = BigDecimal.ONE;
		BigDecimal error = BigDecimal.ONE;
		boolean firstStep = true;

		boolean end = false;

		final BigDecimal aux = new BigDecimal(n + 1).multiply(number, mc);
		final BigDecimal aux4 = new BigDecimal(n - 1).multiply(number, mc);

		do {
			final BigDecimal aux2 = x.pow(n, mc);
			BigDecimal aux3 = new BigDecimal(n - 1).multiply(aux2, mc);
			aux3 = aux3.add(aux, mc);

			BigDecimal aux6 = new BigDecimal(n + 1).multiply(aux2, mc);
			aux6 = aux6.add(aux4);

			final BigDecimal division = aux3.divide(aux6, mc);
			x1 = x.multiply(division, mc);

			BigDecimal errorAux = x1.subtract(x, mc);
			errorAux = errorAux.abs();
			if (error.compareTo(errorAux) != 0 && x1.compareTo(x0) != 0) {
				// error = errorAux;

				// ha aumentado el error cogemos el valor con el menor error
				if (!firstStep && error.compareTo(errorAux) == -1) {
					end = true;
					x1 = x;

				} else {
					firstStep = false;
					error = errorAux;

					if (error.compareTo(BigDecimal.valueOf(0.0125)) < 0) {
						end = true;

					} else {
						x = x1;
					}
				}
			} else {
				end = true;

			}

		} while (!end);

		final BigInteger possibleRoot = x1.toBigInteger();
		final BigInteger possibleRootAddOne = possibleRoot.add(BigInteger.ONE);
		final BigInteger possibleRootSubOne = possibleRoot.subtract(BigInteger.ONE);

		final BigInteger numb = number.toBigInteger();

		if (possibleRoot.pow(n).equals(numb)) {
			return possibleRoot;
		} else if (possibleRootAddOne.pow(n).equals(numb)) {
			return possibleRootAddOne;
		} else if (possibleRootSubOne.pow(n).equals(numb)) {
			return possibleRootSubOne;
		} else {
			return x1;
		}

	}

	/**
	 * Find root order 3.
	 *
	 * @param number the number
	 * @param mc the mathContext
	 * @return the result root
	 * @throws CalculatorException the calculator exception
	 */
	public static ResultRoot findRootOrder3(final BigDecimal number, final MathContext mc) throws CalculatorException {
		Object result = null;

		for (int i = 2; i <= Integer.MAX_VALUE; i++) {
			final BigDecimal aux = nthRootLogaritmDouble(number, i, mc);

			result = nthRootOrder3(number, i, mc, aux);

			if (result instanceof BigInteger) {

				final ResultRoot resultRoot = new ResultRoot();
				resultRoot.setValue((BigInteger) result);
				resultRoot.setOrder(i);
				return resultRoot;
			} else if (aux.compareTo(BigDecimal.valueOf(104743)) == -1) {
				break;
			}

		}

		return null;

	}

	/**
	 * Find root order 3.
	 *
	 * @param number the number
	 * @param mc the mathContext
	 * @param maxOrder the max order
	 * @return the result root
	 * @throws CalculatorException the calculator exception
	 */
	public static ResultRoot findRootOrder3(final BigDecimal number, final MathContext mc, final int maxOrder)
			throws CalculatorException {
		Object result = null;

		for (int i = 2; i <= maxOrder; i++) {
			final BigDecimal aux = nthRootLogaritmDouble(number, i, mc);

			result = nthRootOrder3(number, i, mc, aux);

			if (result instanceof BigInteger) {

				final ResultRoot resultRoot = new ResultRoot();
				resultRoot.setValue((BigInteger) result);
				resultRoot.setOrder(i);
				return resultRoot;

			} else if (aux.compareTo(BigDecimal.valueOf(2)) == -1) {
				break;

			}

		}

		return null;

	}

	/**
	 * Find root order 3.
	 *
	 * @param number the number
	 * @param mc the mc
	 * @param powerList the power list
	 * @return the result root
	 * @throws CalculatorException the calculator exception
	 */
	public static ResultRoot findRootOrder3(final BigDecimal number, final MathContext mc,
			final List<Integer> powerList) throws CalculatorException {
		Object result = null;

		for (final Integer power : powerList) {

			final BigDecimal aux = nthRootLogaritmDouble(number, power, mc);

			result = nthRootOrder3(number, power, mc, aux);

			if (result instanceof BigInteger) {

				final ResultRoot resultRoot = new ResultRoot();
				resultRoot.setValue((BigInteger) result);
				resultRoot.setOrder(power);
				return resultRoot;

			}

		}

		return null;

	}

}
