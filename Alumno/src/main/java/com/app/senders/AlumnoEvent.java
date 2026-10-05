package com.app.senders;

public class AlumnoEvent {

	private long idAlumno;
	private long idCurso;
	
	public AlumnoEvent() {
	}

	public AlumnoEvent(long idAlumno, long idCurso) {
		super();
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
	}

	public long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(long idAlumno) {
		this.idAlumno = idAlumno;
	}

	public long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(long idCurso) {
		this.idCurso = idCurso;
	}
	
	
	
}
