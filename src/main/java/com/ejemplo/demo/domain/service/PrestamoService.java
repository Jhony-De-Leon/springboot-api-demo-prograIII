package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class PrestamoService {

    public PrestamoResponse simular(BigDecimal monto, BigDecimal tasaAnual, int meses) {

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }

        if (tasaAnual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa debe ser mayor a 0");
        }

        if (meses < 1 || meses > 360) {
            throw new IllegalArgumentException("Meses fuera de rango");
        }

        MathContext mc = new MathContext(10);

        BigDecimal tasaMensual = tasaAnual.divide(BigDecimal.valueOf(12), mc)
                                          .divide(BigDecimal.valueOf(100), mc);

        BigDecimal unoMasR = BigDecimal.ONE.add(tasaMensual);
        BigDecimal potencia = unoMasR.pow(meses, mc);

        BigDecimal cuota = monto.multiply(tasaMensual.multiply(potencia, mc), mc)
                .divide(potencia.subtract(BigDecimal.ONE), mc);

        BigDecimal totalPagar = cuota.multiply(BigDecimal.valueOf(meses), mc);
        BigDecimal interesTotal = totalPagar.subtract(monto, mc);

        return new PrestamoResponse(cuota, interesTotal, totalPagar);
    }
}
