package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.AlumnoAltaDTO;
import com.app.dto.AlumnoConCursoDTO;
import com.app.dto.AlumnoDTO;
import com.app.model.Alumno;
import com.app.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public AlumnoDTO altaAlumno(AlumnoAltaDTO dto) {
        Alumno nuevo = new Alumno();
        nuevo.setNombre(dto.getNombre());
        nuevo.setApellido(dto.getApellido());
        nuevo.setDni(dto.getDni());
        nuevo.setEmail(dto.getEmail());

        Alumno guardado = alumnoRepository.save(nuevo);
        return convertirADTO(guardado);
    }

    @Override
    public List<AlumnoDTO> listarAlumnos() {
        return alumnoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
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

    private AlumnoDTO convertirADTO(Alumno alumno) {
        return new AlumnoDTO(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getDni(),
                alumno.getEmail());
    }
}
