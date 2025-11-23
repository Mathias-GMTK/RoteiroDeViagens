package com.roteiroviagens.poo.exception;

public class GeminiException extends RuntimeException {
    public GeminiException(String message) {
        super(message);
    }

    public GeminiException(String message, Throwable cause) {
        super(message, cause);
    }
}
