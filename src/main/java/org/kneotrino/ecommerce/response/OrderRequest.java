package org.kneotrino.ecommerce.response;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private int quantity;
}
