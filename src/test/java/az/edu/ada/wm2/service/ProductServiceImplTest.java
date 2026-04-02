package az.edu.ada.wm2.service;

import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.repository.ProductRepository;
import az.edu.ada.wm2.lab6.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_shouldSaveAndReturnEntity() {
        Product product = new Product();
        product.setProductName("Milk");
        product.setPrice(BigDecimal.TEN);

        Product savedProduct = new Product();
        savedProduct.setId(UUID.randomUUID());
        savedProduct.setProductName("Milk");
        savedProduct.setPrice(BigDecimal.TEN);

        when(productRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.createProduct(product);

        assertEquals(savedProduct.getId(), result.getId());
        verify(productRepository).save(product);
    }

    @Test
    void getProductsByPriceRange_shouldReturnFiltered() {
        Product firstProduct = new Product();
        firstProduct.setId(UUID.randomUUID());
        firstProduct.setProductName("Milk");
        firstProduct.setPrice(BigDecimal.TEN);

        Product secondProduct = new Product();
        secondProduct.setId(UUID.randomUUID());
        secondProduct.setProductName("Bread");
        secondProduct.setPrice(BigDecimal.ONE);

        when(productRepository.findAll()).thenReturn(List.of(firstProduct, secondProduct));

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }
}