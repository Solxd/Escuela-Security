package com.app.service;

import java.util.List;

import com.app.dto.AlumnoAltaDTO;
import com.app.dto.AlumnoConCursoDTO;
import com.app.dto.AlumnoDTO;

public interface AlumnoService {
    AlumnoDTO altaAlumno(AlumnoAltaDTO dto);
    List<AlumnoDTO> listarAlumnos();
    AlumnoConCursoDTO obtenerConCurso(Long alumnoId);
}
