package com.agilesoft.prueba.controller;

import com.agilesoft.prueba.dto.RequestTareaDto;
import com.agilesoft.prueba.entity.Tarea;
import com.agilesoft.prueba.entity.Usuario;
import com.agilesoft.prueba.exception.ModelNotFoundException;
import com.agilesoft.prueba.service.imp.ITareaServiceImp;
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

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private ITareaServiceImp service;

    @GetMapping(value = "/getTareas/{id}", produces = "application/json")
    @Operation(description = "Obtiene las tareas del Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Se obtiene la respuesta satisfactoriamente", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Tarea.class)) })})
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id) throws ModelNotFoundException, Exception{
        List<Tarea> tareas = service.obtenerTareas(id);
        return new ResponseEntity<List<Tarea>>(tareas, HttpStatus.OK);
    }
    @PostMapping(value = "/insert", consumes = "application/json")
    @Operation(description = "Insertar Tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created. La Tarea fue creada correctamente", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = RequestTareaDto.class)) })})
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> guardarTarea(@Valid @RequestBody RequestTareaDto requestTareaDto) throws ModelNotFoundException, Exception {
        service.agregarTarea(requestTareaDto);

        return new ResponseEntity<Object>(requestTareaDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/resolve/{id}", produces = "application/json")
    @Operation(description = "Resulve la tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Se obtiene la respuesta satisfactoriamente", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Tarea.class)) })})
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> resolverTarea(@PathVariable int id) throws ModelNotFoundException, Exception {
        Tarea tarea = service.resolverTarea(id);

        return new ResponseEntity<Object>(tarea, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(description = "Borrar Tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. La tarea fue borrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Not Found. No se encontro la tarea")})
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, Exception {
        service.eliminarTarea(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
