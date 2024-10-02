package com.average.school.average.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

@Data
public class ModificarCalificacionDTO {

    @NotNull(message = "La nueva calificación es obligatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "La calificación mínima es 0.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "La calificación máxima es 10.0")
    private Double nuevaCalificacion;
}
