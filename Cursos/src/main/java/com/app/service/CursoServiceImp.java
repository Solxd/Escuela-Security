package com.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CursoResponseDTO;
import com.app.model.Alumno;
import com.app.model.Curso;
import com.app.repository.AlumnoRepository;
import com.app.repository.CursoRepository;

@Service
public class CursoServiceImp implements CursoService {

    private final CursoRepository cursoRepository;
    private final AlumnoRepository alumnoRepository;

    // Constructor manual para la inyección de dependencias
    public CursoServiceImp(CursoRepository cursoRepository, AlumnoRepository alumnoRepository) {
        this.cursoRepository = cursoRepository;
        this.alumnoRepository = alumnoRepository;
    }


@Override
public CursoResponseDTO altaCursos(Curso curso) {
    Curso guardado = cursoRepository.save(curso);
    return toDto(guardado);
}

@Override
public List<CursoResponseDTO> listarCursos() {
    return cursoRepository.findAll()
            .stream()
            .map(this::toDto)
            .toList();
}

@Override
public Optional<CursoResponseDTO> obtenerPorId(Long id) {
    return cursoRepository.findById(id).map(this::toDto);
}

private CursoResponseDTO toDto(Curso curso) {
    CursoResponseDTO dto = new CursoResponseDTO();

    dto.setId(curso.getId());
    dto.setCiclo_lectivo(curso.getCiclo_lectivo());
    dto.setDivision(curso.getDivision());
    dto.setGrado(curso.getGrado());
    dto.setTurno(curso.getTurno());
    dto.setCupo_maximo(curso.getCupo_maximo());

    return dto;
}
    @Override
    @Transactional
    public void registrarALumno(long idCurso, long idAlumno) {
        Curso curso = this.cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + idCurso));
        
        Alumno alumno = this.alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + idAlumno));
        
        curso.addAlumno(alumno);
        this.cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void sacarALumno(long idCurso, long idAlumno) {
        Curso curso = this.cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + idCurso));
        
        Alumno alumno = this.alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + idAlumno));
        
        curso.removeAlumno(alumno);
        this.cursoRepository.save(curso);
    }
}