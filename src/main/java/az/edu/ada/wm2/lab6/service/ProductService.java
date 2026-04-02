package az.edu.ada.wm2.lab6.service;

import az.edu.ada.wm2.lab6.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
}
