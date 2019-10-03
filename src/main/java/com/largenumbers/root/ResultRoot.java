package com.largenumbers.root;

import java.math.BigInteger;


/**
 * The Class ResultRoot.
 */
public class ResultRoot {

	/** The value. */
	private BigInteger value;

	/** The order. */
	private int order;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public BigInteger getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(final BigInteger value) {
		this.value = value;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(final int order) {
		this.order = order;
	}

}
