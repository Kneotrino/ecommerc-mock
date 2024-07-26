package org.kneotrino.ecommerce.repository;

import org.kneotrino.ecommerce.response.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
