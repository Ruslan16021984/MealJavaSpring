package ru.javawebinar.topjava.utils.exeption;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message){
        super(message);
    }
}
