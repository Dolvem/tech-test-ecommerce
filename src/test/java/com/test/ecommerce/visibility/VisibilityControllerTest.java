package com.test.ecommerce.visibility;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.ecommerce.repository.CsvRepository;
import com.test.ecommerce.repository.domain.ProductDto;
import com.test.ecommerce.repository.domain.SizeDto;
import com.test.ecommerce.repository.domain.StockDto;

@RunWith(MockitoJUnitRunner.class)
public class VisibilityControllerTest {

	@InjectMocks
	private VisibilityController controller;

	@Mock
	private CsvRepository csvRepository;

	@Test
	public void productWithoutStockQuantity() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes()).thenReturn(Sets.newSet(new SizeDto("1", "1", false, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 0L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("A product without stock and not marked as backsoon must not be visible.", "", response);
	}

	@Test
	public void productWithoutStockData() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes()).thenReturn(Sets.newSet(new SizeDto("1", "1", false, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet());
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("A product without stock data and not marked as backsoon must not be visible.", "", response);
	}

	@Test
	public void productWithStock() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes()).thenReturn(Sets.newSet(new SizeDto("1", "1", false, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 1L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("A product with only normal sizes and stock must be visible.", "1", response);
	}

	@Test
	public void productWithoutStockButBackSoon() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes()).thenReturn(Sets.newSet(new SizeDto("1", "1", true, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 1L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("A product with only normal sizes with no stock and backsoon must be visible.", "1", response);
	}

	@Test
	public void specialSizeWithStock() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes()).thenReturn(Sets.newSet(new SizeDto("1", "1", false, true)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 1L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("Products with special sizes need normal sizes to be visible.", "", response);
	}

	@Test
	public void specialProductVisible() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes())
				.thenReturn(Sets.newSet(new SizeDto("1", "1", false, true), new SizeDto("2", "1", false, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 1L), new StockDto("2", 1L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("Products with special sizes and normal sizes must be visible.", "1", response);
	}

	@Test
	public void specialProductWithSpecialBackSoon() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes())
				.thenReturn(Sets.newSet(new SizeDto("1", "1", true, true), new SizeDto("2", "1", false, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 0L), new StockDto("2", 1L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals(
				"Products with special sizes that have no stock but marked as backsoon and normal sizes with stock must be visible.",
				"1", response);
	}

	@Test
	public void specialProductWithNormalBackSoon() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 1L)));
		when(csvRepository.getSizes())
				.thenReturn(Sets.newSet(new SizeDto("1", "1", false, true), new SizeDto("2", "1", true, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 1L), new StockDto("2", 0L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals(
				"Products with special sizes with stock and normal sizes that have no stock but marked as backsoon must be visible.",
				"1", response);
	}

	@Test
	public void sortedProducts() {
		// Given
		when(csvRepository.getProducts()).thenReturn(Sets.newSet(new ProductDto("1", 2L), new ProductDto("2", 1L)));
		when(csvRepository.getSizes())
				.thenReturn(Sets.newSet(new SizeDto("1", "1", false, false), new SizeDto("2", "2", false, false)));
		when(csvRepository.getStock()).thenReturn(Sets.newSet(new StockDto("1", 1L), new StockDto("2", 1L)));
		// When
		String response = controller.getProductsVisibility();
		// Then
		assertEquals("Two visible products must follow the sequence order", "2,1", response);
	}

}
