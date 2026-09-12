public class InvalidDimensionException extends Exception {
    public InvalidDimensionException(String message) {
        super(message);
    }

    public InvalidDimensionException(String message, Throwable cause){ super(message, cause);}
}
