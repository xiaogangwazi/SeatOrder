package exception;

/**
 * 自定义异常
 */
public class URLIsNullException extends RuntimeException {
    public URLIsNullException(String message){
        super(message);
    }
}
