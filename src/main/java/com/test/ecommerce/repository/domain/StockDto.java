package com.test.ecommerce.repository.domain;

/**
 * The Class StockDto.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
public class StockDto {
	private String sizeId;
	private long quantity;

	/**
	 * Instantiates a new stock dto.
	 *
	 * @param sizeId   the size id
	 * @param quantity the quantity
	 */
	public StockDto(String sizeId, long quantity) {
		super();
		this.sizeId = sizeId;
		this.quantity = quantity;
	}

	/**
	 * Gets the size id.
	 *
	 * @return the size id
	 */
	public String getSizeId() {
		return sizeId;
	}

	/**
	 * Sets the size id.
	 *
	 * @param sizeId the new size id
	 */
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
