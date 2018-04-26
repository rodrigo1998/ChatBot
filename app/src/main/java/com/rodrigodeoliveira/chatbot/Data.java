package com.rodrigodeoliveira.chatbot;

public class Data {

    String key;

    String nome;
    String email;

    public Data(String key, String name, String email) {
        this.key = key;
        this.nome = name;

        this.email = email;
    }

    public Data() {
    }



    public String getKey() {
        return key;
    }

    public String getNome() {
        return nome;
    }



    public String getEmail() {
        return email;
    }
}
