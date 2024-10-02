package com.average.school.average.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AlumnoDTO {

    @NotBlank(message = "El nombre del alumno es obligatorio")
    private String nombre;

}