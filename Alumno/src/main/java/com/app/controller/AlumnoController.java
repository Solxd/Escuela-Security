package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AlumnoAltaDTO;
import com.app.dto.AlumnoConCursoDTO;
import com.app.dto.AlumnoDTO;
import com.app.service.AlumnoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/alumnos")
@SecurityRequirement(name = "bearerAuth")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> altaAlumno(@RequestBody AlumnoAltaDTO dto) {
        AlumnoDTO creado = alumnoService.altaAlumno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> listarAlumnos() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @GetMapping("/{id}/con-curso")
    public ResponseEntity<AlumnoConCursoDTO> obtenerConCurso(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.obtenerConCurso(id));
    }
}

