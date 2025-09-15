package ra.session11.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ra.session11.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByProductNameContaining(String search, Pageable pageable);

    Page<Product> findTop10ByProductNameContaining( String search, Pageable pageable);
}
