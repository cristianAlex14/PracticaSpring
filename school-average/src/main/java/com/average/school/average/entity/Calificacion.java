package com.average.school.average.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_id", nullable = false)
    @JsonIgnore
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    @JsonIgnore
    private Materia materia;

    @Column(nullable = false)
    @NotNull(message = "El bimestre es obligatorio")
    @DecimalMin(value = "1", inclusive = true, message = "El bimestre debe ser al menos 1")
    @DecimalMax(value = "5", inclusive = true, message = "El bimestre no puede ser mayor que 5")
    private Integer bimestre;

    @Column(nullable = false)
    @NotNull(message = "La calificación es obligatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "La calificación mínima es 0.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "La calificación máxima es 10.0")
    private Double calificacion;

    public Calificacion(Alumno alumno, Materia materia, Integer bimestre, Double calificacion) {
        this.alumno = alumno;
        this.materia = materia;
        this.bimestre = bimestre;
        this.calificacion = calificacion;
    }

}
