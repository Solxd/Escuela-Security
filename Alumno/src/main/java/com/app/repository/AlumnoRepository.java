package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}