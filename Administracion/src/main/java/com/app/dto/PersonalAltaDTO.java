package com.app.dto;

import com.app.Enum.Cargo; // Se corrigió "Enum" a minúscula "enums" por convención Java

public class PersonalAltaDTO {

    private String nombre;
    private String apellido;
    private String email;
    private Cargo cargo;

    // 1. Constructor vacío
    public PersonalAltaDTO() {
    }

    // 2. Constructor completo
    public PersonalAltaDTO(String nombre, String apellido, String email, Cargo cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.cargo = cargo;
    }

    // 3. Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}