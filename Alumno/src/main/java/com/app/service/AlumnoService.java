package com.app.service;

import java.util.List;
import com.app.dto.AlumnoConCursoDTO;
import com.app.model.Alumno;

public interface AlumnoService {
    Alumno altaAlumno(Alumno alumno);
    List<Alumno> listarAlumnos();
    AlumnoConCursoDTO obtenerConCurso(Long alumnoId);
}