package com.app.dto;

public class CursoDTO {
    private Long id;
    private String ciclo_lectivo;
    private String division;
    private String grado;
    private String turno;
    private String cupo_maximo;
    private DocenteDTO docente;   

    public CursoDTO() {
	}
    
	public CursoDTO(Long id, String ciclo_lectivo, String division, String grado, String turno, String cupo_maximo,
			DocenteDTO docente) {
		super();
		this.id = id;
		this.ciclo_lectivo = ciclo_lectivo;
		this.division = division;
		this.grado = grado;
		this.turno = turno;
		this.cupo_maximo = cupo_maximo;
		this.docente = docente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCiclo_lectivo() {
		return ciclo_lectivo;
	}
	public void setCiclo_lectivo(String ciclo_lectivo) {
		this.ciclo_lectivo = ciclo_lectivo;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getCupo_maximo() {
		return cupo_maximo;
	}
	public void setCupo_maximo(String cupo_maximo) {
		this.cupo_maximo = cupo_maximo;
	}
	public DocenteDTO getDocente() {
		return docente;
	}
	public void setDocente(DocenteDTO docente) {
		this.docente = docente;
	}

    
}