package parser;

public class ParserException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public ParserException(String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public ParserException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public ParserException (Throwable exception) {
        super(exception);
    }

}

