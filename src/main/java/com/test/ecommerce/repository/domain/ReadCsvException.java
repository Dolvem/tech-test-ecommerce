package com.test.ecommerce.repository.domain;

/**
 * The Class ReadCsvException.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
public class ReadCsvException extends RuntimeException {
	private static final long serialVersionUID = -6829886551422280601L;

	/**
	 * Instantiates a new read csv exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public ReadCsvException(String message, Throwable cause) {
		super(message, cause);
	}

}
