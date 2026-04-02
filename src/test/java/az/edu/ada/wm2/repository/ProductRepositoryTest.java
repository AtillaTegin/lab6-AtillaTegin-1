package az.edu.ada.wm2.repository;

import az.edu.ada.wm2.lab6.Lab6Application;
import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Lab6Application.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
        void save_shouldPersistProduct() {
                Product product = new Product();
                product.setName("Milk");
                product.setPrice(BigDecimal.TEN);

                Product savedProduct = productRepository.save(product);
                List<Product> products = productRepository.findAll();

                assertNotNull(savedProduct.getId());
                assertEquals(1, products.size());
                assertEquals("Milk", products.get(0).getName());
    }
}