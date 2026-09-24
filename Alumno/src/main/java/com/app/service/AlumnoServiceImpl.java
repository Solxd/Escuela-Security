package com.app.service;

import com.app.dto.AlumnoConCursoDTO;
import com.app.model.Alumno;
import com.app.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    // CONSTRUCTOR MANUAL (Inyección de dependencias de Spring)
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Alumno altaAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public AlumnoConCursoDTO obtenerConCurso(Long alumnoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + alumnoId));

        AlumnoConCursoDTO dto = new AlumnoConCursoDTO();
        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setDni(alumno.getDni());
        dto.setEmail(alumno.getEmail());

        return dto;
    }
}