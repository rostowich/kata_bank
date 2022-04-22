package com.lacombedulionvert.kata_bank;

public class NotEnoughAmountException extends Exception{
    public NotEnoughAmountException(String message) {
        super(message);
    }
}
