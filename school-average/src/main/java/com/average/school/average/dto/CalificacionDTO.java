package com.average.school.average.dto;

import lombok.Data;

@Data
public class CalificacionDTO {
    private Long alumnoId;
    private Long materiaId;
    private int bimestre;
    private double calificacion;
}