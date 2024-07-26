package org.kneotrino.ecommerce.controller;

import org.kneotrino.ecommerce.repository.ProductRepository;
import org.kneotrino.ecommerce.response.OrderRequest;
import org.kneotrino.ecommerce.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> orderProduct(@RequestBody OrderRequest orderRequest) {
        Optional<Product> productOpt = productRepository.findById(orderRequest.getProductId());
        if (productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product product = productOpt.get();
        if (product.getQuantity() < orderRequest.getQuantity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient product quantity");
        }
        product.setQuantity(product.getQuantity() - orderRequest.getQuantity());
        productRepository.save(product);
        return ResponseEntity.ok("Order placed successfully");
    }
}
