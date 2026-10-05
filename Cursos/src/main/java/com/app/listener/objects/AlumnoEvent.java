package com.app.listener.objects;

public class AlumnoEvent {

    private long idAlumno;
    private long idCurso;

    public AlumnoEvent() {
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