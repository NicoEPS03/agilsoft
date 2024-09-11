package com.agilesoft.prueba.service;

import com.agilesoft.prueba.dto.RequestTareaDto;
import com.agilesoft.prueba.entity.Tarea;
import com.agilesoft.prueba.exception.ModelNotFoundException;

import java.util.List;

public interface ITareaService {
    public List<Tarea> obtenerTareas(Integer id) throws ModelNotFoundException, Exception;
    public void agregarTarea(RequestTareaDto requestTareaDto) throws ModelNotFoundException, Exception;
    public Tarea resolverTarea(Integer id) throws ModelNotFoundException, Exception;
    public void eliminarTarea(Integer id) throws ModelNotFoundException, Exception;
}
