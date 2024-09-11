package com.agilesoft.prueba.repository;

import com.agilesoft.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario u WHERE u.username = :username AND u.password = :password", nativeQuery = true)
    public Usuario login(@Param("username") String username, @Param("password") String password);

    Optional<Usuario> findByUsername(String username);
}
