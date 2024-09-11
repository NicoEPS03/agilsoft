package com.agilesoft.prueba;

import com.agilesoft.prueba.dto.AuthResponseDto;
import com.agilesoft.prueba.dto.RequestLoginDto;
import com.agilesoft.prueba.entity.Usuario;
import com.agilesoft.prueba.service.imp.IUsuarioServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUsuarioServiceImp serviceImp;
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(UsuarioControllerTest.class);
    }

    @Test
    void crearUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Nicolas Peralta");
        usuario.setUsername("neps");
        usuario.setPassword("12312");
        doNothing().when(serviceImp).crearUsuario(any());
        mockMvc.perform(post("/user/insert")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated());
    }

    @Test
    void getUserButNoLogit() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Nicolas Peralta");
        usuario.setUsername("neps");

        int userId = 1;
        when(serviceImp.obtenerUsuario("")).thenReturn(usuario);

        // Convertir el usuario a JSON
        String expectedJson = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(get("/user/getUser", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
