package com.test.ecommerce.repository.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.test.ecommerce.repository.CsvRepository;
import com.test.ecommerce.repository.domain.ProductDto;
import com.test.ecommerce.repository.domain.ReadCsvException;
import com.test.ecommerce.repository.domain.SizeDto;
import com.test.ecommerce.repository.domain.StockDto;

/**
 * The Class CsRepositoryImpl.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
@Repository
public class CsvRepositoryImpl implements CsvRepository {

	private static final Function<String[], ProductDto> PRODUCT_MAPPER = item -> new ProductDto(item[0],
			Long.parseLong(item[1]));
	private static final Function<String[], SizeDto> SIZE_MAPPER = item -> new SizeDto(item[0], item[1],
			Boolean.parseBoolean(item[2]), Boolean.parseBoolean(item[3]));
	private static final Function<String[], StockDto> STOCK_MAPPER = item -> new StockDto(item[0],
			Long.parseLong(item[1]));

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	@Override
	public Set<ProductDto> getProducts() {
		return readCsv("src/main/resources/csv/product.csv").stream().map(PRODUCT_MAPPER).collect(Collectors.toSet());
	}

	/**
	 * Gets the sizes.
	 *
	 * @return the sizes
	 */
	@Override
	public Set<SizeDto> getSizes() {
		return readCsv("src/main/resources/csv/size.csv").stream().map(SIZE_MAPPER).collect(Collectors.toSet());
	}

	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	@Override
	public Set<StockDto> getStock() {
		return readCsv("src/main/resources/csv/stock.csv").stream().map(STOCK_MAPPER).collect(Collectors.toSet());
	}

	/**
	 * Read csv.
	 *
	 * @param fileName the file name
	 * @return the list
	 */
	private List<String[]> readCsv(String fileName) {

		try (CSVReader reader = new CSVReader(new FileReader(new File(fileName)))) {
			return reader.readAll();
		} catch (IOException | CsvException e) {
			throw new ReadCsvException("Error reading the CSV file " + fileName, e);
		}
	}

}
