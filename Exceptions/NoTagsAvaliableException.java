package Exceptions;

public class NoTagsAvaliableException extends RuntimeException {

    public NoTagsAvaliableException() {
        super();
    }

    public NoTagsAvaliableException(String message) {
        super(message);
    }

}
