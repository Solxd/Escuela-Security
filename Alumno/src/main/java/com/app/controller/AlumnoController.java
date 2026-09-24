package com.app.controller;

import com.app.dto.AlumnoAltaDTO;
import com.app.dto.AlumnoConCursoDTO;
import com.app.model.Alumno;
import com.app.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public ResponseEntity<Alumno> altaAlumno(@RequestBody AlumnoAltaDTO dto) {
        Alumno nuevo = new Alumno();
        
        // Cambiado de dto.nombre() a dto.getNombre()
        nuevo.setNombre(dto.getNombre());
        nuevo.setApellido(dto.getApellido());
        nuevo.setDni(dto.getDni());
        nuevo.setEmail(dto.getEmail());
        
        Alumno creado = alumnoService.altaAlumno(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> listarAlumnos() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @GetMapping("/{id}/con-curso")
    public ResponseEntity<AlumnoConCursoDTO> obtenerConCurso(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.obtenerConCurso(id));
    }
}