
package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.CursoResponseDTO;
import com.app.model.Curso;

public interface CursoService {

    CursoResponseDTO altaCursos(Curso curso);

    void registrarALumno(long idCurso, long idAlumno);

    void sacarALumno(long idCurso, long idAlumno);

    List<CursoResponseDTO> listarCursos();

    Optional<CursoResponseDTO> obtenerPorId(Long id);
}