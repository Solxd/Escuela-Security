package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PersonalAltaDTO;
import com.app.model.Curso;
import com.app.model.Personal;
import com.app.service.PersonalService;

@RestController
@RequestMapping("/personal")
public class PersonalController {

	private final PersonalService personalService;

	public PersonalController(PersonalService personalService) {
		this.personalService = personalService;
	}

	@PostMapping
	public ResponseEntity<Personal> altaPersonal(@RequestBody PersonalAltaDTO dto) {
	    Personal nuevo = new Personal();
	    nuevo.setNombre(dto.nombre());
	    nuevo.setApellido(dto.apellido());
	    nuevo.setEmail(dto.email());
	    nuevo.setCargo(dto.cargo());

	    Personal creado = personalService.altaPersonal(nuevo);
	    return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	@GetMapping
	public ResponseEntity<List<Personal>> listarPersonal() {
		return ResponseEntity.ok(personalService.listarPersonal());
	}
	 @GetMapping("/{id}")
	    public ResponseEntity<Personal> obtenerPorId(@PathVariable Long id) {
	        return personalService.obtenerPorId(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }
}