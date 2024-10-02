package com.average.school.average.repository;

import com.average.school.average.entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByAlumnoIdAndMateriaId(Long alumnoId, Long materiaId);

    List<Calificacion> findByAlumnoId(Long alumnoId);

    @Query("SELECT AVG(c.calificacion) FROM Calificacion c WHERE c.materia.id = :materiaId")
    Double calcularPromedioMateria(@Param("materiaId") Long materiaId);

    @Query("SELECT AVG(c.calificacion) FROM Calificacion c WHERE c.materia.id = :materiaId AND c.bimestre = :bimestre")
    Double calcularPromedioMateriaPorBimestre(@Param("materiaId") Long materiaId, @Param("bimestre") int bimestre);

    @Query("SELECT AVG(c.calificacion) FROM Calificacion c WHERE c.alumno.id = :alumnoId")
    Double calcularPromedioAlumno(@Param("alumnoId") Long alumnoId);

    @Query("SELECT AVG(c.calificacion) FROM Calificacion c WHERE c.alumno.id = :alumnoId AND c.bimestre = :bimestre")
    Double calcularPromedioAlumnoPorBimestre(@Param("alumnoId") Long alumnoId, @Param("bimestre") int bimestre);
}
