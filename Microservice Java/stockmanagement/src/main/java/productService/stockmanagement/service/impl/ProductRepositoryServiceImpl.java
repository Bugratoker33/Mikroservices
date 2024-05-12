package productService.stockmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import productService.stockmanagement.enums.Language;
import productService.stockmanagement.exception.Enums.FriendlyMessageCodes;
import productService.stockmanagement.exception.exceptions.ProductAlreadyException;
import productService.stockmanagement.exception.exceptions.ProductNotCreatedException;
import productService.stockmanagement.exception.exceptions.ProductNotFoundExceptions;
import productService.stockmanagement.repository.Entity.Product;
import productService.stockmanagement.repository.Entity.ProductRepository;
import productService.stockmanagement.request.ProductCreateRequest;
import productService.stockmanagement.request.ProductUpdateRequest;
import productService.stockmanagement.service.IPorductRepositoryService;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j//log log.logtype gibi yapabilir traze= izlemke istenilen lohlar için kulanılır
@Service//BUSSİNES LOGİCLERİNİ BELİRLEDİ
@RequiredArgsConstructor //producrepositor için lazım
public class ProductRepositoryServiceImpl implements IPorductRepositoryService {

    private final ProductRepository productRepository;

    @Override
    public Product creatProduct(Language language, ProductCreateRequest productCreateRequest) {
       log.debug("[{}][creatProduct] ->request:{}",this.getClass().getSimpleName(),productCreateRequest);
       try {
           Product product=Product.builder()
                   .ProductName(productCreateRequest.getProductName())
                   .quantity(productCreateRequest.getQuantity())
                   .price(productCreateRequest.getPrice())
                   .deleted(false)
                   .build();
           Product productResponse =productRepository.save(product);
           log.debug("[{}][createProduct] -> response: {}",this.getClass().getSimpleName(),productResponse);
           return productResponse;
       }catch (Exception exception){
           throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREARED_EXCEPTION,"product request: "+productCreateRequest.toString());
       }

    }

    @Override
    public Product getProduct(Language language, Long productId) {
        log.debug("[{}][getProduct] ->request:{}",this.getClass().getSimpleName(),productId);

        Product product = productRepository.getByProductIdAndDeletedFalse(productId);//veri tabanında bu var mı ;
        if(Objects.isNull(product))
        {
            throw new ProductNotFoundExceptions(language,FriendlyMessageCodes.PRODUCT_NOT_CREARED_EXCEPTION,"ProductNotFound for product id"+ productId);
        }
        log.debug("[{}][getProduct] -> response: {}",this.getClass().getSimpleName(),product);
        return product;
    }

    @Override
    public List<Product> getProductS(Language language) {
        log.debug("[{}][getProductS] ->request:{}",this.getClass().getSimpleName());
        List<Product> products=productRepository.getAllByDeletedFalse();
        if (products.isEmpty()){
            throw new ProductNotFoundExceptions(language,FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION,"ProductNotFoun");
        }
        log.debug("[{}][getProductS] -> response: {}",this.getClass().getSimpleName());

        return products;
    }

    @Override
    public Product updatePrdouct(Language language,Long productId, ProductUpdateRequest productUpdateRequest ) {
        log.debug("[{}][updatePrdouct] ->request:{}",this.getClass().getSimpleName(),productUpdateRequest);
        Product product = getProduct(language, productId);
        product.setProductName(productUpdateRequest.getProductName());
        product.setQuantity(productUpdateRequest.getQuantity());
        product.setPrice(productUpdateRequest.getPrice());
        product.setProductCreatedDate(product.getProductCreatedDate());
        product.setPrductUpdateDate(new Date());
        Product productResponse=productRepository.save(product);

        log.debug("[{}][updatePrdouct] -> response: {}",this.getClass().getSimpleName(),productResponse);
      return productResponse;

    }

    @Override
    public Product deleteProducct(Language language, Long productId) {
        log.debug("[{}][deleteProducct] ->request productId:{}",this.getClass().getSimpleName(),productId);
        Product product ;
        try {
            product=getProduct(language,productId);
            product.setDeleted(true);
            product.setPrductUpdateDate(new Date());
            Product productResponse =productRepository.save(product);
            log.debug("[{}][deleteProducct] ->respose productId:{}",this.getClass().getSimpleName(),productId);
         return productResponse;
        }
        catch (ProductNotFoundExceptions productNotFoundExceptions){
            throw  new ProductAlreadyException(language,FriendlyMessageCodes.PRODUCT_ALREDY_DELETED,"Product olredy deleted"+productId);
        }

    }
}
