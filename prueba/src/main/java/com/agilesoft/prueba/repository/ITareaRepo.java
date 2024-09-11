package com.agilesoft.prueba.repository;

import com.agilesoft.prueba.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITareaRepo extends JpaRepository<Tarea, Integer> {
    @Query(value = "SELECT * FROM tarea t WHERE t.id_usuario = :id", nativeQuery = true)
    public List<Tarea> listaTareas(@Param("id") Integer id);
}
