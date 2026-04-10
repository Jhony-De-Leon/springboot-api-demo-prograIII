package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@Tag(name = "Préstamos") 
@RestController
@RequestMapping("/api/v1/simulaciones")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @Operation(
            summary = "Simular préstamo",
            description = "Calcula cuota mensual, interés total y total a pagar"
            )
    
    
    @PostMapping("/prestamo")
    public ResponseEntity<PrestamoResponse> simularPrestamo(
            @Valid @RequestBody PrestamoRequest request
    ) {
        return ResponseEntity.ok(
                prestamoService.simular(
                        request.monto(),
                        request.tasaAnual(),
                        request.meses()
                )
        );
    }
}