package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.model.Personal;
import com.app.repository.PersonalRepository;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalServiceImpl(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public Personal altaPersonal(Personal personal) {
        personal.setId(null); // el alta nunca acepta un id externo — lo genera la base
        return personalRepository.save(personal);
    }

    @Override
    public List<Personal> listarPersonal() {
        return personalRepository.findAll();
    }

	@Override
	public Optional<Personal> obtenerPorId(Long id) {
        return personalRepository.findById(id);
	
	}

}