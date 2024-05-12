package productService.stockmanagement.exception.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import productService.stockmanagement.enums.Language;
import productService.stockmanagement.exception.Enums.IfriendlyMessageCode;
import productService.stockmanagement.exception.utils.FriendlyMessageUtils;

@Slf4j//log YAPACAĞIMIZ İÇİN
@Getter
public class ProductNotCreatedException extends  RuntimeException{
    private final Language language;
    private final IfriendlyMessageCode friendlyMessageCode;

    public ProductNotCreatedException(Language language, IfriendlyMessageCode friendlyMessageCode,String message) {//değişkenler ctor da inisiliz edildi
        super(FriendlyMessageUtils.getFriendlyMessage(language,friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[ProductNotCreatedExceptions] -> message:{} developer message {}",FriendlyMessageUtils.getFriendlyMessage(language ,friendlyMessageCode),message);

    }
}
