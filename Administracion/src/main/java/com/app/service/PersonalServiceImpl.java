package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.dto.PersonalResponseDTO;
import com.app.model.Personal;
import com.app.repository.PersonalRepository;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalServiceImpl(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }


@Override
public PersonalResponseDTO altaPersonal(Personal personal) {

    personal.setId(null);

    Personal guardado = personalRepository.save(personal);

    return toDto(guardado);
}

@Override
public List<PersonalResponseDTO> listarPersonal() {
    return personalRepository.findAll()
            .stream()
            .map(this::toDto)
            .toList();
}

@Override
public Optional<PersonalResponseDTO> obtenerPorId(Long id) {
    return personalRepository.findById(id).map(this::toDto);
}

private PersonalResponseDTO toDto(Personal personal) {
    PersonalResponseDTO dto = new PersonalResponseDTO();

    dto.setId(personal.getId());
    dto.setNombre(personal.getNombre());
    dto.setApellido(personal.getApellido());
    dto.setEmail(personal.getEmail());
    dto.setCargo(personal.getCargo());

    return dto;
}
}