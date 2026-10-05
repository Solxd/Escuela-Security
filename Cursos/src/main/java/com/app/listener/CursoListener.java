package com.app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.app.listener.objects.AlumnoEvent;
import com.app.model.Alumno;
import com.app.model.Curso;
import com.app.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class CursoListener {

    private final CursoRepository cursoRepository;

    public CursoListener(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @RabbitListener(queues = "alumno.alta.queue")
    public void recibirAltaAlumno(AlumnoEvent evento) {
        Curso curso = cursoRepository.findById(evento.getIdCurso())
                .orElseThrow(() -> new RuntimeException(
                        "Curso no encontrado con id: " + evento.getIdCurso()));

        curso.addAlumno(new Alumno(evento.getIdAlumno()));
        cursoRepository.save(curso);

        System.out.println("Se recibió evento: alumno " + evento.getIdAlumno()
                + " en curso " + evento.getIdCurso());
    }
}