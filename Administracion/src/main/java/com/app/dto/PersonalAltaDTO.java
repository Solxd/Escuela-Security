package com.app.dto;

import com.app.Enum.Cargo;

public record PersonalAltaDTO(
    String nombre,
    String apellido,
    String email,
    Cargo cargo
) {}