package fr.diginamic.qualairapi.exceptions;

public class AppException extends RuntimeException{

    public AppException(String msg, Exception exception){
        super(msg, exception);
    }
    public  AppException(String msg){
        super(msg);
    }
}
