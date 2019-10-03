package com.largenumbers.root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.junit.Test;

public class TestLargeNumbers {

	@Test
	public void testRootBigIntger() {

		// Integer
		final BigInteger integerNumber = new BigInteger("12345678901234567890");
		final BigInteger powered = integerNumber.pow(1000);

		// Cast to Decimal
		final BigDecimal number = new BigDecimal(powered);

		// Creating MathContext
		final int numberOfDig = BigDecimalUtil.exponent(number);
		final MathContext mc = new MathContext(numberOfDig);

		// Root number
		final int nth_root = 1000;

		BigDecimal x0 = BigDecimal.ZERO;
		final long time1 = System.currentTimeMillis();
		try {
			x0 = Root.nthRootLogaritmDouble(number, nth_root, mc);
		} catch (final CalculatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Object result = Root.nthRootOrder3(number, nth_root, mc, x0);
		final long time2 = System.currentTimeMillis() - time1;

		if (result instanceof BigInteger) {
			// case when the root is an integer
			assertEquals(result, integerNumber);
			System.out.println("root:" + result);
			System.out.println("nth_root:" + nth_root);
			System.out.println("number of the digits:" + numberOfDig);
			System.out.println("calculation time:" + time2 + " ms");

		} else if (result instanceof BigDecimal) {
			// case when the root is not an integer
			assertTrue(false);
		}

	}

	@Test
	public void testRootBigDecimal() {

		// Integer
		final BigInteger integerNumber = new BigInteger("12345678901234567890");
		final BigInteger powered = integerNumber.pow(1000);

		// Cast to Decimal
		final BigDecimal number = new BigDecimal(powered);

		// Creating MathContext
		final int numberOfDig = BigDecimalUtil.exponent(number);
		final MathContext mc = new MathContext(numberOfDig);

		// Root number
		final int nth_root = 900;// not integer result

		BigDecimal x0 = BigDecimal.ZERO;
		final long time1 = System.currentTimeMillis();
		try {
			x0 = Root.nthRootLogaritmDouble(number, nth_root, mc);
		} catch (final CalculatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Object result = Root.nthRootOrder3(number, nth_root, mc, x0);
		final long time2 = System.currentTimeMillis() - time1;

		if (result instanceof BigInteger) {
			// case when the root is an integer
			assertTrue(false);

		} else if (result instanceof BigDecimal) {
			// case when the root is not an integer

			final BigDecimal bd = (BigDecimal) result;

			System.out.println("root:" + new DecimalFormat("#0.############").format(bd));
			System.out.println("nth_root:" + nth_root);
			System.out.println("number of the digits:" + numberOfDig);
			System.out.println("calculation time:" + time2 + " ms");
			assertTrue(true);
		}

	}

}