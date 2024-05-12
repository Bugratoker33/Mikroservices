package productService.stockmanagement.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse<T> {
    //rest api için tek tipte response yapısı oluştururz;
    //response ,,hata mesajı,succes mesajı,,http statu gibi tüm alanları barındırır;
    private FriendlyMessage friendlyMessage;
    private T payload;
    private boolean hasError;
    private List<String> _errorMessages;
    private HttpStatus httpStatus;

}
