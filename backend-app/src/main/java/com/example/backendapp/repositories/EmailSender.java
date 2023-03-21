package com.example.backendapp.repositories;

public interface EmailSender {
    void send(String to, String email);
}
