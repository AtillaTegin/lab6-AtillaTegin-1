package az.edu.ada.wm2.lab6.service;

import az.edu.ada.wm2.lab6.model.dto.ProductRequestDto;
import az.edu.ada.wm2.lab6.model.dto.ProductResponseDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto dto);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getProductsExpiringBefore(LocalDate date);
    List<ProductResponseDto> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
