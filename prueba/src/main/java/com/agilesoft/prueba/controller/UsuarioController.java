package com.agilesoft.prueba.controller;

import com.agilesoft.prueba.dto.RequestLoginDto;
import com.agilesoft.prueba.entity.Usuario;
import com.agilesoft.prueba.exception.ModelNotFoundException;
import com.agilesoft.prueba.service.imp.IUsuarioServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private IUsuarioServiceImp service;

    @GetMapping(value = "/getUser", produces = "application/json")
    @Operation(description = "Obtiene la informacion de Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Se obtiene la respuesta satisfactoriamente", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Usuario.class)) })})
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> obtenerUsuario(@RequestHeader(value = "Authorization", defaultValue = "") String authHeader) throws ModelNotFoundException, Exception{
        String token = authHeader.substring(7);
        Usuario usuario = service.obtenerUsuario(token);

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Insertar Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. El Usuario fue creado correctamente", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Usuario.class)) })})
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario){
        service.crearUsuario(usuario);

        return new ResponseEntity<Object>(usuario, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    @Operation(description = "Login Usuario")
    public ResponseEntity<?> login(@Valid @RequestBody RequestLoginDto requestLoginDto) throws ModelNotFoundException, Exception {

        String usuario = service.login(requestLoginDto);

        return new ResponseEntity<Object>(usuario, HttpStatus.OK);
    }
}
