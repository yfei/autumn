package cn.young.autumn.data.jpa.filter.exception;


@SuppressWarnings("serial")
public class InvalidFilterException extends RuntimeException {

    /**
     * @param msg
     * @param cause
     */
    public InvalidFilterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * @param msg
     */
    public InvalidFilterException(String msg) {
        super(msg);
    }

}
