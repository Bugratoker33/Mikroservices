package productService.stockmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productService.stockmanagement.enums.Language;
import productService.stockmanagement.exception.Enums.FriendlyMessageCodes;
import productService.stockmanagement.exception.utils.FriendlyMessageUtils;
import productService.stockmanagement.repository.Entity.Product;
import productService.stockmanagement.request.ProductCreateRequest;
import productService.stockmanagement.request.ProductUpdateRequest;
import productService.stockmanagement.response.FriendlyMessage;
import productService.stockmanagement.response.InternalApiResponse;
import productService.stockmanagement.response.ProductResponse;
import productService.stockmanagement.service.IPorductRepositoryService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j//log ekleme işlemş
@RestController//http isteklerine cevap verme
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductControler {
    private final IPorductRepositoryService porductRepositoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/products")
    public InternalApiResponse<List<ProductResponse>> getProducts(@PathVariable("language")Language language){
        log.debug("[{}][updateProduct]",this.getClass().getSimpleName());
        List<Product> productos= porductRepositoryService.getProductS(language);
        List<ProductResponse> productResponses= convertProductResponseList(productos);
        log.debug("[{}][updateProduct] -> response: {}",this.getClass().getSimpleName());
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language")Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request:{}",this.getClass().getSimpleName(),productCreateRequest);
        Product product= porductRepositoryService.creatProduct(language,productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response:{}",this.getClass().getSimpleName(),productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language , FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language ,FriendlyMessageCodes.PRODUCT_SUCCESSFULY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language")Language language,
                                                           @PathVariable("productId") Long productId){
        log.debug("[{}][getProduct] -> request:{}",this.getClass().getSimpleName(),productId);
        Product product=porductRepositoryService.getProduct(language,productId);
        ProductResponse productResponse= convertProductResponse(product);
        log.debug("[{}][getProduct] -> response:{}",this.getClass().getSimpleName(),productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)

                .build();

    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{language}/updates/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable("language")Language language,
                                                              @PathVariable("productId") Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest){
        log.debug("[{}][updateProduct] -> request:{}",this.getClass().getSimpleName(),productId,productUpdateRequest);
        Product product= porductRepositoryService.updatePrdouct(language ,productId,productUpdateRequest);
        ProductResponse productResponse= convertProductResponse(product);
        log.debug("[{}][updateProduct] -> response:{}",this.getClass().getSimpleName(),productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();

    }




    private List<ProductResponse>convertProductResponseList(List<Product>productsList){
        return productsList.stream()
                .map(arg -> ProductResponse.builder()
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .quantity(arg.getQuantity())
                        .price(arg.getPrice())
                        .productCreatedDate(arg.getProductCreatedDate().getTime())
                        .productUpdatedDate(arg.getPrductUpdateDate().getTime())
                        .build())
                .collect(Collectors.toList());
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{language}/delete/{productId}")
    public InternalApiResponse<ProductResponse> deleteProduct(@PathVariable("language" ) Language language,
                                                              @PathVariable("productId") Long productId ){
        log.debug("[{}][deleteProducct] ->request productId:{}",this.getClass().getSimpleName(),productId);
        Product  product=porductRepositoryService.deleteProducct(language ,productId);
        ProductResponse productResponse=convertProductResponse(product);
        log.debug("[{}][deleteProducct] ->respose productId:{}",this.getClass().getSimpleName(),productId);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language ,FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_ALREDY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }
    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getPrductUpdateDate().getTime())
                .build();
    }
}
