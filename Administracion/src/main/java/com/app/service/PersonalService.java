package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Curso;
import com.app.model.Personal;

public interface PersonalService {

    Personal altaPersonal(Personal personal);
    Optional<Personal> obtenerPorId(Long id);

    List<Personal> listarPersonal();

}