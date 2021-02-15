package com.test.ecommerce.visibility;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.ecommerce.repository.CsvRepository;
import com.test.ecommerce.repository.domain.ProductDto;
import com.test.ecommerce.repository.domain.SizeDto;
import com.test.ecommerce.repository.domain.StockDto;

/**
 * The Class VisibilityController.
 *
 * @author David Molero Herrera - d.molero.h@gmail.com
 */
@RestController
@RequestMapping("/visibility")
public class VisibilityController {

	@Autowired
	private CsvRepository csvRepository;

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProductsVisibility() {

		Set<ProductDto> visibleProducts = new LinkedHashSet<>();

		Set<ProductDto> products = csvRepository.getProducts();
		Set<SizeDto> sizes = csvRepository.getSizes();
		Set<StockDto> stock = csvRepository.getStock();

		// Exclude irrelevant sizes
		Map<String, List<SizeDto>> sizesByProduct = sizes.stream()
				.filter(size -> products.stream().anyMatch(product -> product.getId().equals(size.getProductId())))
				.collect(Collectors.groupingBy(SizeDto::getProductId));

		// Map with sizes by product
		Map<ProductDto, List<SizeDto>> productsWithSizes = products.stream()
				.collect(Collectors.toMap(Function.identity(), product -> sizesByProduct.get(product.getId())));

		// If the configured criteria is valid the productId is added to the response
		for (Entry<ProductDto, List<SizeDto>> product : productsWithSizes.entrySet()) {
			if (isProductVisible(product.getValue(), stock)) {
				visibleProducts.add(product.getKey());
			}
		}

		// Sort and return visible products
		Set<String> result = new LinkedHashSet<>();
		visibleProducts.stream().sorted((p1, p2) -> Long.compare(p1.getSequence(), p2.getSequence()))
				.forEach(p -> result.add(p.getId()));

		return StringUtils.join(result, ',');
	}

	/**
	 * Checks if is product visible.
	 *
	 * @param sizes the sizes
	 * @param stock the stock
	 * @return true, if is product visible
	 */
	private boolean isProductVisible(List<SizeDto> sizes, Set<StockDto> stock) {
		boolean hasSpecial = false;
		int visibleSizes = 0;

		for (SizeDto size : sizes) {
			// Save if a size is special
			hasSpecial = hasSpecial || size.isSpecial();

			if (isSizeVisible(size, findStock(size.getId(), stock))) {
				// Stores counter of visible sizes
				visibleSizes++;
			}
		}
		// Has a special & no special visible sizes or one visible size with no special
		// size
		return (hasSpecial && visibleSizes >= 2) || (!hasSpecial && visibleSizes >= 1);
	}

	/**
	 * Find stock.
	 *
	 * @param sizeId the size id
	 * @param stock  the stock
	 * @return the stock dto, can be null
	 */
	private StockDto findStock(String sizeId, Set<StockDto> stock) {
		return stock.stream().filter(s -> s.getSizeId().equals(sizeId)).findFirst().orElse(null);
	}

	/**
	 * Checks if is size visible.
	 *
	 * @param size  the size
	 * @param stock the stock, may be null
	 * @return true, if is size visible
	 */
	private boolean isSizeVisible(SizeDto size, StockDto stock) {
		return size.isBackSoon() || (stock != null && stock.getQuantity() > 0L);
	}

}
