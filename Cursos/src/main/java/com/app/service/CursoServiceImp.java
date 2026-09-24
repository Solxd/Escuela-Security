package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.client.DocenteClient;
import com.app.dto.CursoConDocenteDTO;
import com.app.dto.DocenteDTO;
import com.app.model.Alumno;
import com.app.model.Curso;
import com.app.repository.AlumnoRepository;
import com.app.repository.CursoRepository;

@Service
public class CursoServiceImp implements CursoService {

    private final CursoRepository cursoRepository;
    private final AlumnoRepository alumnoRepository;
    
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
	public void registrarALumno(long idCurso, long idAlumno) {
		Curso curso =this.cursoRepository.findById(idCurso).get();
		Alumno alumno = new Alumno();
		alumno.setId(idAlumno);
		curso.addAlumno(alumno);
		this.cursoRepository.save(curso);
	}

	@Override
	public void sacarALumno(long idCurso, long idAlumno) {
		Curso curso =this.cursoRepository.findById(idCurso).get();
		Alumno alumno = this.alumnoRepository.findById(idAlumno).get();
		curso.removeAlumno(alumno);
	}


}