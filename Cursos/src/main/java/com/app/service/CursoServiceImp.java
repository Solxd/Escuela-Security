package com.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Curso altaCursos(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
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