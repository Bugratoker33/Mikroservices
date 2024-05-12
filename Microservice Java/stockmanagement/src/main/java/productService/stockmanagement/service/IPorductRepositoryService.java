package productService.stockmanagement.service;

import productService.stockmanagement.enums.Language;
import productService.stockmanagement.repository.Entity.Product;
import productService.stockmanagement.request.ProductCreateRequest;
import productService.stockmanagement.request.ProductUpdateRequest;

import java.util.List;

public interface IPorductRepositoryService {
    Product creatProduct (Language language, ProductCreateRequest productCreateRequest);

    Product getProduct(Language language,Long prductId);

    List<Product> getProductS (Language language);

    Product updatePrdouct(Language language ,Long productId, ProductUpdateRequest productUpdateRequest);

    Product deleteProducct(Language language,Long productId);
}
