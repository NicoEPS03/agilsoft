package com.agilesoft.prueba.service;

import com.agilesoft.prueba.dto.RequestLoginDto;
import com.agilesoft.prueba.entity.Usuario;
import com.agilesoft.prueba.exception.ModelNotFoundException;

public interface IUsuarioService {
    public Usuario obtenerUsuario(String token) throws ModelNotFoundException, Exception;
    public void crearUsuario(Usuario usuario);

    public String login(RequestLoginDto requestLoginDto) throws ModelNotFoundException, Exception;

}
