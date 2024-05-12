package productService.stockmanagement.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import productService.stockmanagement.exception.Enums.FriendlyMessageCodes;
import productService.stockmanagement.exception.exceptions.ProductAlreadyException;
import productService.stockmanagement.exception.exceptions.ProductNotCreatedException;
import productService.stockmanagement.exception.exceptions.ProductNotFoundExceptions;
import productService.stockmanagement.exception.utils.FriendlyMessageUtils;
import productService.stockmanagement.response.FriendlyMessage;
import productService.stockmanagement.response.InternalApiResponse;

import java.util.Collections;

@RestControllerAdvice//EXCEPTİONS HANDELER YAPABİLMEK
public class GlobalExceptionsHandeler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreatedException.class)
    public InternalApiResponse<String> handleProductNotCreatedExceptions(ProductNotCreatedException exception){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.Error))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                ._errorMessages(Collections.singletonList(exception.getMessage()))
                .build();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundExceptions.class)
    public InternalApiResponse<String>handleProductNotFoundExceptions(ProductNotFoundExceptions productNotFoundExceptions){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(productNotFoundExceptions.getLanguage(),FriendlyMessageCodes.Error))
                        .description(FriendlyMessageUtils.getFriendlyMessage(productNotFoundExceptions.getLanguage(),productNotFoundExceptions.getIfriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.NOT_FOUND)
                .hasError(true)
                ._errorMessages(Collections.singletonList(productNotFoundExceptions.getMessage()))
                .build();

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductAlreadyException.class)
    public InternalApiResponse<String> handelProductAlreadyDeletedExceptions (ProductAlreadyException exception){

        return  InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),FriendlyMessageCodes.Error))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(),exception.getIfriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                ._errorMessages(Collections.singletonList(exception.getMessage()))

                .build();
    }
}
