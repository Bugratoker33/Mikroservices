package productService.stockmanagement.exception.Enums;

public enum FriendlyMessageCodes implements IfriendlyMessageCode{
    OK(1000),
    Error(1001),
    SUCCESS(1002),
    PRODUCT_NOT_CREARED_EXCEPTION(1500),
    PRODUCT_SUCCESSFULY_CREATED(1501),
    PRODUCT_NOT_FOUND_EXCEPTION(1502),
    PRODUCT_SUCCESFULLY_UPDATED(1503),
    PRODUCT_ALREDY_DELETED(1504),
    PRODUCT_SUCCESFULLY_DELETED(1505);


    private final int value;
    FriendlyMessageCodes(int value){
        this.value=value;
    }

    //burada bize enum demek ;

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
