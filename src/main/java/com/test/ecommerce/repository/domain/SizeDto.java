package com.test.ecommerce.repository.domain;

/**
 * The Class SizeDto.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
public class SizeDto {
	private String id;
	private String productId;
	private boolean backSoon;
	private boolean special;

	/**
	 * Instantiates a new size dto.
	 *
	 * @param id the id
	 * @param productId the product id
	 * @param backSoon the back soon
	 * @param special the special
	 */
	public SizeDto(String id, String productId, boolean backSoon, boolean special) {
		super();
		this.id = id;
		this.productId = productId;
		this.backSoon = backSoon;
		this.special = special;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Checks if is back soon.
	 *
	 * @return true, if is back soon
	 */
	public boolean isBackSoon() {
		return backSoon;
	}

	/**
	 * Sets the back soon.
	 *
	 * @param backSoon the new back soon
	 */
	public void setBackSoon(boolean backSoon) {
		this.backSoon = backSoon;
	}

	/**
	 * Checks if is special.
	 *
	 * @return true, if is special
	 */
	public boolean isSpecial() {
		return special;
	}

	/**
	 * Sets the special.
	 *
	 * @param special the new special
	 */
	public void setSpecial(boolean special) {
		this.special = special;
	}

}
