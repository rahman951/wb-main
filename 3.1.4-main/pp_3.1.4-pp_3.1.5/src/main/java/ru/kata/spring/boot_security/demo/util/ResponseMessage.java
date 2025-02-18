package ru.kata.spring.boot_security.demo.util; // Замените на ваш пакет

public class ResponseMessage {
    private String message;

    // Конструктор
    public ResponseMessage(String message) {
        this.message = message;
    }

    // Геттер
    public String getMessage() {
        return message;
    }

    // Сеттер
    public void setMessage(String message) {
        this.message = message;
    }
}
