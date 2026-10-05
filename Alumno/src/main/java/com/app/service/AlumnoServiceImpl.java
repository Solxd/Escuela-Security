package com.app.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.app.config.RabbitMQConfig;
import com.app.dto.AlumnoAltaDTO;
import com.app.dto.AlumnoConCursoDTO;
import com.app.dto.AlumnoDTO;
import com.app.model.Alumno;
import com.app.repository.AlumnoRepository;
import com.app.senders.AlumnoEvent;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final RabbitTemplate rabbitTemplate;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, RabbitTemplate rabbitTemplate) {
        this.alumnoRepository = alumnoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public AlumnoDTO altaAlumno(AlumnoAltaDTO dto) {
        if (dto.getCursoId() == null) {
        	throw new IllegalArgumentException("El alumno debe tener un curso asignado");      
        	}

        Alumno guardado = alumnoRepository.save(dto.mapperTo());

        AlumnoEvent event = new AlumnoEvent();
        event.setIdAlumno(guardado.getId());
        event.setIdCurso(guardado.getIdCurso());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event);

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