package Exceptions;

public class CannotAddRelationException extends RuntimeException {
    
    public CannotAddRelationException() {
        super("Cannot add this relation. Please check if the concepts used was already added in concept map");
    }
    
}
