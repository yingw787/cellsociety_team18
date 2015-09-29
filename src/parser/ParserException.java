package parser;

/**
 */
public class ParserException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     * @param message String
     * @param values Object[]
     */
    public ParserException(String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     * @param cause Throwable
     * @param message String
     * @param values Object[]
     */
    public ParserException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     * @param exception Throwable
     */
    public ParserException (Throwable exception) {
        super(exception);
    }

}

