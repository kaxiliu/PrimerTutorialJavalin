package com.dam;

public class User {
    // Nota: los campos pueden ser públicos para GSON o privados con getters/setters.
    public int id;
    public String name;
    public String email;
    // Constructor para crear instancias desde Java (ej. en mocks)
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // Constructor vacío que GSON necesita para deserializar JSON a objetos User
    public User() {
    }
}