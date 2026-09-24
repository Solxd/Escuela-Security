package com.app.dto;

public class DocenteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String cargo;   // acá como String, no como enum — te explico por qué abajo    
  
    public DocenteDTO() {
	}
	public DocenteDTO(Long id, String nombre, String apellido, String email, String cargo) {
		super();
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}



}