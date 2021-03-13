public class BusinessException extends RuntimeException{
    private AppException appException;

    BusinessException(AppException appException){
        this.appException = appException;
    }

    public AppException getAppException() {
        return appException;
    }
}