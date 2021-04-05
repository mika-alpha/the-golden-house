package model;

public class PasswordMismatchException extends Exception{

    public PasswordMismatchException(String message){
        super(message);
    }
}
