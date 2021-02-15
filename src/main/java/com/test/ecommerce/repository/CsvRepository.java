package com.test.ecommerce.repository;

import java.util.Set;

import com.test.ecommerce.repository.domain.ProductDto;
import com.test.ecommerce.repository.domain.SizeDto;
import com.test.ecommerce.repository.domain.StockDto;

/**
 * The Interface CsvRepository.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
public interface CsvRepository {

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	Set<ProductDto> getProducts();

	/**
	 * Gets the sizes.
	 *
	 * @return the sizes
	 */
	Set<SizeDto> getSizes();

	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	Set<StockDto> getStock();
}
