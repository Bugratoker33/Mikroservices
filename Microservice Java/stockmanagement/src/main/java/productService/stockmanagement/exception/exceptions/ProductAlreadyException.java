package productService.stockmanagement.exception.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import productService.stockmanagement.enums.Language;
import productService.stockmanagement.exception.Enums.FriendlyMessageCodes;
import productService.stockmanagement.exception.Enums.IfriendlyMessageCode;
import productService.stockmanagement.exception.utils.FriendlyMessageUtils;

@Slf4j
@Getter
public class ProductAlreadyException extends RuntimeException{
    private final Language language;//final ekle
    private final IfriendlyMessageCode ifriendlyMessageCode;

    public ProductAlreadyException(Language language, IfriendlyMessageCode ifriendlyMessageCode ,String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language,ifriendlyMessageCode));
        this.language = language;
        this.ifriendlyMessageCode = ifriendlyMessageCode;
        log.error("[ProductAlreadyException] -> developer message: {}", FriendlyMessageUtils.getFriendlyMessage(language,ifriendlyMessageCode),message);

    }
}
