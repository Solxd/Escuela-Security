package com.app.dto;

public class AlumnoAltaDTO {

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private Long cursoId;

    // Constructor vacío
    public AlumnoAltaDTO() {
    }

    // Constructor completo
    public AlumnoAltaDTO(String nombre, String apellido, String dni, String email, Long cursoId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.cursoId = cursoId;
    }

    // Getters y Setters
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}