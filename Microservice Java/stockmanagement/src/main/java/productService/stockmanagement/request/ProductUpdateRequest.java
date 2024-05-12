package productService.stockmanagement.request;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private long productId;
    private String productName;
    private  Integer quantity ;
    private Double price;


}
