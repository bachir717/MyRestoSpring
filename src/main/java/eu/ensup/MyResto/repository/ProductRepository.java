package eu.ensup.MyResto.repository;

import eu.ensup.MyResto.domaine.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
