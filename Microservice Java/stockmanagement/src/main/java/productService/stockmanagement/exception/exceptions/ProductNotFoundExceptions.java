package productService.stockmanagement.exception.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import productService.stockmanagement.enums.Language;
import productService.stockmanagement.exception.Enums.IfriendlyMessageCode;
import productService.stockmanagement.exception.utils.FriendlyMessageUtils;

@Slf4j
@Getter
public class ProductNotFoundExceptions extends RuntimeException {
    private final Language language;
    private final IfriendlyMessageCode ifriendlyMessageCode;

    public ProductNotFoundExceptions(Language language, IfriendlyMessageCode ifriendlyMessageCode ,String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language,ifriendlyMessageCode));
        this.language = language;
        this.ifriendlyMessageCode = ifriendlyMessageCode;
        log.error("[ProductNotFoundExceptions] -> message:{} developer message: {}",FriendlyMessageUtils.getFriendlyMessage(language,ifriendlyMessageCode),message);
    }

}
