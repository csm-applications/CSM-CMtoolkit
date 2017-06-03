package Exceptions;

public class InvalidTagInsideFileException extends RuntimeException {
    
    public InvalidTagInsideFileException(String file) {
        super("There is a invalid tag inside one file\n [" + file + "]");
    }
    
}
