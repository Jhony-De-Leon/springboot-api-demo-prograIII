package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.domain.service.SaludoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; 
import com.ejemplo.demo.api.exception.GlobalExceptionHandler;

import java.time.Instant;

import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean; 
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaludoController.class)
@Import(GlobalExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false) 

class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    @Test
    @DisplayName("GET /saludos debe responder mensaje correcto")
    void deberiaResponderSaludoGet() throws Exception {

        when(saludoService.crearSaludo("Ana"))
                .thenReturn(new SaludoResponse("Hola, Ana. Bienvenido a Spring Boot 3!", Instant.now()));

        mockMvc.perform(get("/api/v1/saludos?nombre=Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Hola, Ana. Bienvenido a Spring Boot 3!"));
    }

    @Test
    @DisplayName("POST /saludos debe responder OK")
    void deberiaResponderPostCorrecto() throws Exception {

        String json = """
        {
            "nombre": "Ana"
        }
        """;

        when(saludoService.crearSaludo("Ana"))
                .thenReturn(new SaludoResponse("Hola, Ana. Bienvenido a Spring Boot 3!", Instant.now()));

        mockMvc.perform(post("/api/v1/saludos")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    @DisplayName("POST /saludos debe fallar validación")
    void deberiaFallarValidacion() throws Exception {

        String json = """
        {
            "nombre": ""
        }
        """;

        mockMvc.perform(post("/api/v1/saludos")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}
