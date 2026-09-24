package com.app.dto;

public record CursoAltaDTO(
    String ciclo_lectivo,
    String division,
    String grado,
    String turno,
    String cupo_maximo,
    Long docenteId
) {}