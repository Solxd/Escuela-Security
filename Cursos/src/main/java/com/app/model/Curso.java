package com.app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ciclo_lectivo;
    private String division;
    private String grado;
    private String turno;
    private String cupo_maximo;

    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Personal docente;
		
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> alumnos = new ArrayList<>();
	
    // Constructor vacío
    public Curso() {
    }

    // Constructor completo
    public Curso(Long id, String ciclo_lectivo, String division, String grado, String turno, String cupo_maximo,
                 Personal docente, List<Alumno> alumnos) {
        this.id = id;
        this.ciclo_lectivo = ciclo_lectivo;
        this.division = division;
        this.grado = grado;
        this.turno = turno;
        this.cupo_maximo = cupo_maximo;
        this.docente = docente;
        this.alumnos = alumnos;
    }

    // Métodos Helper para la relación bidireccional
    public void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
        alumno.setCurso(this);
    }

    public void removeAlumno(Alumno alumno) {
        alumnos.remove(alumno);
        alumno.setCurso(null);
    }

    // Getters y Setters
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

    public Personal getDocente() {
        return docente;
    }

    public void setDocente(Personal docente) {
        this.docente = docente;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}