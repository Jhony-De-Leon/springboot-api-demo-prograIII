package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PrestamoRequest(

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
        BigDecimal monto,

        @NotNull(message = "La tasa anual es obligatoria")
        @DecimalMin(value = "0.01", message = "La tasa debe ser mayor a 0")
        BigDecimal tasaAnual,

        @Min(value = 1, message = "Mínimo 1 mes")
        @Max(value = 360, message = "Máximo 360 meses")
        int meses
) {}