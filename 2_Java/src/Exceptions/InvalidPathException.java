package Exceptions;

public class InvalidPathException extends RuntimeException {
    
    public InvalidPathException() {
        super("Invalid Path to file");
    }
    
}
