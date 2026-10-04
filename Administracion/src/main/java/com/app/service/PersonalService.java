
package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.PersonalResponseDTO;
import com.app.model.Personal;

public interface PersonalService {

    PersonalResponseDTO altaPersonal(Personal personal);

    Optional<PersonalResponseDTO> obtenerPorId(Long id);

    List<PersonalResponseDTO> listarPersonal();
}