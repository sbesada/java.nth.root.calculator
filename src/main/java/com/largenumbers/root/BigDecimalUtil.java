package com.largenumbers.root;

import java.math.BigDecimal;

/**
 * The Class BigDecimalUtil.
 */
public class BigDecimalUtil {

	/**
	 * Exponent.
	 *
	 * @param value the value
	 * @return the int
	 */
	public static int exponent(final BigDecimal value) {
		return value.precision() - value.scale() - 1;
	}

	/**
	 * Mantissa.
	 *
	 * @param value the value
	 * @return the big decimal
	 */
	public static BigDecimal mantissa(final BigDecimal value) {
		final int exponent = exponent(value);
		if (exponent == 0) {
			return value;
		}

		return value.movePointLeft(exponent);
	}
}
