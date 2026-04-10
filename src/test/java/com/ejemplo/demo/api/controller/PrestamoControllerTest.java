package com.ejemplo.demo.api.controller;

  
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import java.math.BigDecimal;

@WebMvcTest(PrestamoController.class)
@AutoConfigureMockMvc(addFilters = false)
class PrestamoControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
	private PrestamoService prestamoService;
    	
    	@Test
    	void debeCalcularPrestamoCorrectamente() throws Exception {

    	    when(prestamoService.simular(any(), any(), anyInt()))
    	            .thenReturn(new PrestamoResponse(
    	                    new BigDecimal("888.00"),
    	                    new BigDecimal("656.00"),
    	                    new BigDecimal("10656.00")
    	            ));

    	    String body = """
    	    {
    	      "monto": 10000,
    	      "tasaAnual": 12,
    	      "meses": 12
    	    }
    	    """;

    	    mockMvc.perform(post("/api/v1/simulaciones/prestamo")
    	            .contentType(MediaType.APPLICATION_JSON)
    	            .content(body))
    	            .andExpect(status().isOk())
    	            .andExpect(jsonPath("$.cuotaMensual").exists());
    	}
}