package az.edu.ada.wm2.lab6.repository;

import az.edu.ada.wm2.lab6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
