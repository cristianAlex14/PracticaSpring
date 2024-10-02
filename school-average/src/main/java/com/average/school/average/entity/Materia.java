package com.average.school.average.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "materias", fetch = FetchType.LAZY)
    @JsonIgnore // Evita la recursión infinita durante la serialización JSON
    private List<Alumno> alumnos;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita la recursión infinita durante la serialización JSON
    private List<Calificacion> calificaciones;

    public Materia(String nombre) {
        this.nombre = nombre;
    }

}