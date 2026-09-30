package com.app.dto;

public class AlumnoConCursoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private CursoDTO curso;

    public AlumnoConCursoDTO() {
    }

    public AlumnoConCursoDTO(Long id, String nombre, String apellido, String dni, String email, CursoDTO curso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.curso = curso;
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

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }
}