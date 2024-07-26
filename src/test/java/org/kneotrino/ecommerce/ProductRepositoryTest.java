package org.kneotrino.ecommerce;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.kneotrino.ecommerce.repository.ProductRepository;
import org.kneotrino.ecommerce.response.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest extends EcommerceApplicationTests {

    @Autowired
    ProductRepository productRepository;


    @Test
    void contextLoads() {
        assertNotNull(productRepository);
    }



    @Test
    void insertProduct() {
        Product product = new Product();
        product.setName("insertProduct");
        product.setPrice(new BigDecimal("0"));
        product.setQuantity(0);
        product.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        product.setUpdatedAt(new Timestamp(new java.util.Date().getTime()));

        Product save = productRepository.save(product);
        assertNotNull(save);
        List<Product> all = productRepository.findAll();
        assertEquals(all.size(),1);
    }
}