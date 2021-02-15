package com.test.ecommerce.repository.domain;

/**
 * The Class ProductDto.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
public class ProductDto {
	private String id;
	private long sequence;

	/**
	 * Instantiates a new product dto.
	 *
	 * @param id       the id
	 * @param sequence the sequence
	 */
	public ProductDto(String id, long sequence) {
		super();
		this.id = id;
		this.sequence = sequence;
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
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public long getSequence() {
		return sequence;
	}

	/**
	 * Sets the sequence.
	 *
	 * @param sequence the new sequence
	 */
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

}
