package az.edu.ada.wm2.service;

import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.model.dto.ProductRequestDto;
import az.edu.ada.wm2.lab6.model.dto.ProductResponseDto;
import az.edu.ada.wm2.lab6.model.mapper.ProductMapper;
import az.edu.ada.wm2.lab6.repository.CategoryRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_shouldSaveAndReturnDto() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setProductName("Milk");
        requestDto.setPrice(BigDecimal.TEN);

        Product entity = new Product();
        entity.setProductName("Milk");
        entity.setPrice(BigDecimal.TEN);

        UUID savedId = UUID.randomUUID();
        Product savedProduct = new Product();
        savedProduct.setId(savedId);
        savedProduct.setProductName("Milk");
        savedProduct.setPrice(BigDecimal.TEN);

        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(savedId);
        responseDto.setProductName("Milk");
        responseDto.setPrice(BigDecimal.TEN);

        when(productMapper.toEntity(requestDto)).thenReturn(entity);
        when(productRepository.save(entity)).thenReturn(savedProduct);
        when(productMapper.toResponseDto(savedProduct)).thenReturn(responseDto);

        ProductResponseDto result = productService.createProduct(requestDto);

        assertEquals(savedId, result.getId());
        assertEquals("Milk", result.getProductName());
        verify(productRepository).save(entity);
    }

    @Test
    void getAllProducts_shouldReturnDtoList() {
        Product firstProduct = new Product();
        firstProduct.setId(UUID.randomUUID());
        firstProduct.setProductName("Milk");
        firstProduct.setPrice(BigDecimal.TEN);

        Product secondProduct = new Product();
        secondProduct.setId(UUID.randomUUID());
        secondProduct.setProductName("Bread");
        secondProduct.setPrice(BigDecimal.ONE);

        ProductResponseDto dto1 = new ProductResponseDto();
        dto1.setProductName("Milk");
        ProductResponseDto dto2 = new ProductResponseDto();
        dto2.setProductName("Bread");

        when(productRepository.findAll()).thenReturn(List.of(firstProduct, secondProduct));
        when(productMapper.toResponseDto(firstProduct)).thenReturn(dto1);
        when(productMapper.toResponseDto(secondProduct)).thenReturn(dto2);

        List<ProductResponseDto> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }
}