package com.agilesoft.prueba;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.agilesoft.prueba.controller.TareaController;
import com.agilesoft.prueba.dto.RequestTareaDto;
import com.agilesoft.prueba.entity.Tarea;
import com.agilesoft.prueba.exception.ModelNotFoundException;
import com.agilesoft.prueba.repository.ITareaRepo;
import com.agilesoft.prueba.service.ITareaService;
import com.agilesoft.prueba.service.imp.ITareaServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ITareaServiceImp tareaService;

    @Test
    public void testAgregarTareaSuccess() throws Exception {
        RequestTareaDto requestTareaDto = new RequestTareaDto();
        requestTareaDto.setIdUsuario(1);
        requestTareaDto.setNombre("hacer test");
        requestTareaDto.setDescripcion("Pasar todos los test");

        mockMvc.perform(post("/tarea/insert")
                        .contentType("application/json")
                        .content("{\"idUsuario\":1, \"nombre\":\"hacer test\", \"descripcion\":\"Pasar todos los test\"}"))
                .andExpect(status().isForbidden());
    }

}