package com.app.client;

import com.app.dto.DocenteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "modulo-administracion")
public interface DocenteClient {

    @GetMapping("/personal/{id}")
    DocenteDTO obtenerPorId(@PathVariable("id") Long id);
}