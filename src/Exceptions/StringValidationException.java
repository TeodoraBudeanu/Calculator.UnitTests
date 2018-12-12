package Exceptions;

public class StringValidationException extends Exception {
    public StringValidationException(String errorMessage){
        super(errorMessage);
    }
}
