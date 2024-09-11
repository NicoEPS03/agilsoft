package com.agilesoft.prueba.service.imp;

import com.agilesoft.prueba.dto.RequestTareaDto;
import com.agilesoft.prueba.entity.Tarea;
import com.agilesoft.prueba.exception.ModelNotFoundException;
import com.agilesoft.prueba.repository.ITareaRepo;
import com.agilesoft.prueba.repository.IUsuarioRepo;
import com.agilesoft.prueba.service.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ITareaServiceImp implements ITareaService {
    @Autowired
    private ITareaRepo tareaRepo;
    @Autowired
    private IUsuarioRepo usuarioRepo;
    @Override
    public List<Tarea> obtenerTareas(Integer id) {
        return tareaRepo.listaTareas(id);
    }

    @Override
    public void agregarTarea(RequestTareaDto requestTareaDto) throws ModelNotFoundException, Exception {
        if (usuarioRepo.existsById(requestTareaDto.getIdUsuario())){
            Tarea tarea = new Tarea();
            tarea.setNombre(requestTareaDto.getNombre());
            tarea.setDescripcion(requestTareaDto.getDescripcion());
            tarea.setIdUsuario(requestTareaDto.getIdUsuario());
            tarea.setEstado("No Resulto");
            tarea.setFechaCreacion(LocalDateTime.now());
            tarea.setFechaActualizacion(LocalDateTime.now());
            tareaRepo.save(tarea);
        }else
            throw new ModelNotFoundException("No se encontró el usuario");

    }

    @Override
    public Tarea resolverTarea(Integer id) throws ModelNotFoundException, Exception {
        if(tareaRepo.existsById(id)){
            Tarea tarea = tareaRepo.findById(id).get();
            tarea.setEstado("Resuelto");
            tarea.setFechaActualizacion(LocalDateTime.now());
            return tareaRepo.save(tarea);
        }else
            throw new ModelNotFoundException("No se encontró la tarea");
    }

    @Override
    public void eliminarTarea(Integer id) throws ModelNotFoundException, Exception {
        if(tareaRepo.existsById(id)){
            tareaRepo.deleteById(id);
        }else
            throw new ModelNotFoundException("No se encontró la tarea");
    }
}
