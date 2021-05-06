package com.aped.service;

import com.aped.dto.UsuarioDTO;
import com.aped.entity.UsuarioEntity;
import com.aped.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public List<UsuarioDTO> buscarUsuarios(){
        List<UsuarioEntity> usuarioEntities = repository.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for(UsuarioEntity entity : usuarioEntities){
            UsuarioDTO usuarioDTO = new UsuarioDTO(entity.getId(), entity.getNome());
            usuarioDTOS.add(usuarioDTO);
        }

        return usuarioDTOS;
    }
}
