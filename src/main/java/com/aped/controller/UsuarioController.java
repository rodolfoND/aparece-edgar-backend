package com.aped.controller;

import com.aped.dto.UsuarioDTO;
import com.aped.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @GetMapping
    public List<UsuarioDTO> buscarUsuarios(){
        return service.buscarUsuarios();
    }
}
