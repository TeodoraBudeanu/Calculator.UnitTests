package main.exceptions;

public class UMValidationException extends Exception {
    public UMValidationException(String errorMessage){
        super(errorMessage);
    }
}
