package com.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.dto.CursoAltaDTO;
import com.app.model.Curso;
import com.app.service.CursoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearerAuth")
public class CursoController {

    private final CursoService cursoService;

    // Constructor manual para la inyección de dependencias
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> altaCursos(@RequestBody CursoAltaDTO dto) {
        Curso nuevo = new Curso();
        
        // Uso de los getters tradicionales de la clase CursoAltaDTO
        nuevo.setCiclo_lectivo(dto.getCiclo_lectivo());
        nuevo.setDivision(dto.getDivision());
        nuevo.setGrado(dto.getGrado());
        nuevo.setTurno(dto.getTurno());
        nuevo.setCupo_maximo(dto.getCupo_maximo());

        Curso creado = cursoService.altaCursos(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        return cursoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}