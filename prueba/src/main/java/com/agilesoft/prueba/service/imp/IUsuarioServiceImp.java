package com.agilesoft.prueba.service.imp;

import com.agilesoft.prueba.auth.JwtService;
import com.agilesoft.prueba.dto.AuthResponseDto;
import com.agilesoft.prueba.dto.RequestLoginDto;
import com.agilesoft.prueba.entity.Usuario;
import com.agilesoft.prueba.exception.ModelNotFoundException;
import com.agilesoft.prueba.repository.IUsuarioRepo;
import com.agilesoft.prueba.service.IUsuarioService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class IUsuarioServiceImp implements IUsuarioService {
    @Autowired
    private IUsuarioRepo repo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Usuario obtenerUsuario(String token) throws ModelNotFoundException, Exception {
        Claims claims = jwtService.decodeToken(token);
        Usuario usuario =  repo.findByUsername(claims.getSubject()).orElseThrow();
        if(repo.existsById(usuario.getId())){
            return repo.findById(usuario.getId()).get();
        }else
            throw new ModelNotFoundException("No se encontró el usuario");
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repo.save(usuario);
    }

    @Override
    public String login(RequestLoginDto requestLoginDto) throws ModelNotFoundException, Exception{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLoginDto.getUsername(), requestLoginDto.getPassword()));
        UserDetails user = repo.findByUsername(requestLoginDto.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build().toString();
    }
}
