package com.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.dto.CursoAltaDTO;
import com.app.model.Curso;
import com.app.service.CursoService;
import com.app.dto.CursoResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearerAuth")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }


@PostMapping
public ResponseEntity<CursoResponseDTO> altaCursos(
        @RequestBody CursoAltaDTO dto) {

    Curso nuevo = new Curso();

    nuevo.setCiclo_lectivo(dto.getCiclo_lectivo());
    nuevo.setDivision(dto.getDivision());
    nuevo.setGrado(dto.getGrado());
    nuevo.setTurno(dto.getTurno());
    nuevo.setCupo_maximo(dto.getCupo_maximo());

    CursoResponseDTO creado = cursoService.altaCursos(nuevo);

    return ResponseEntity.status(HttpStatus.CREATED).body(creado);
}


@GetMapping
public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
    return ResponseEntity.ok(cursoService.listarCursos());
}


@GetMapping("/{id}")
public ResponseEntity<CursoResponseDTO> obtenerPorId(
        @PathVariable Long id) {

    return cursoService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
}