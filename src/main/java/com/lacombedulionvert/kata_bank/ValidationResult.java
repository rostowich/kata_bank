package com.lacombedulionvert.kata_bank;

public enum ValidationResult{
    SUCCESS ("SUCCESS"),
    AMOUNT_REQUIRED ("The amount is required"),
    AMOUNT_IS_NOT_VALID ("The amount is invalid");

    private final String message;

    ValidationResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

