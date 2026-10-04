
package com.app.dto;

import com.app.Enum.Cargo;

public class PersonalResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Cargo cargo;

    public PersonalResponseDTO() {
    }

    public PersonalResponseDTO(Long id, String nombre,
            String apellido, String email, Cargo cargo) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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