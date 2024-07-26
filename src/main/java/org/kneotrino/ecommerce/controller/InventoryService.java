package org.kneotrino.ecommerce.controller;

import org.kneotrino.ecommerce.repository.ProductRepository;
import org.kneotrino.ecommerce.response.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/inventory/products")
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("message", "Product added successfully");
            put("product_id", savedProduct.getProductId());
        }});
    }

    @PutMapping("/{productId}/quantity")
    public ResponseEntity<?> updateProductQuantity(@PathVariable Long productId, @RequestBody Map<String, Integer> update) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product product = productOpt.get();
        product.setQuantity(update.get("quantity"));
        productRepository.save(product);
        return ResponseEntity.ok("Product quantity updated successfully");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> checkProductAvailability(@PathVariable Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(productOpt.get());
    }
}
