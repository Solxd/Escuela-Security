package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
