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

import com.app.dto.PersonalAltaDTO;
import com.app.dto.PersonalResponseDTO;
import com.app.model.Personal;
import com.app.service.PersonalService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/personal")
@SecurityRequirement(name = "bearerAuth")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }


@PostMapping
public ResponseEntity<PersonalResponseDTO> altaPersonal(
        @RequestBody PersonalAltaDTO dto) {

    Personal nuevo = new Personal();

    nuevo.setNombre(dto.getNombre());
    nuevo.setApellido(dto.getApellido());
    nuevo.setEmail(dto.getEmail());
    nuevo.setCargo(dto.getCargo());

    PersonalResponseDTO creado = personalService.altaPersonal(nuevo);

    return ResponseEntity.status(HttpStatus.CREATED).body(creado);
}


@GetMapping
public ResponseEntity<List<PersonalResponseDTO>> listarPersonal() {
    return ResponseEntity.ok(personalService.listarPersonal());
}

@GetMapping("/{id}")
public ResponseEntity<PersonalResponseDTO> obtenerPorId(
        @PathVariable Long id) {

    return personalService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
}