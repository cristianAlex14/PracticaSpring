package com.average.school.average.service;

import com.average.school.average.dto.AlumnoDTO;
import com.average.school.average.dto.CalificacionDTO;
import com.average.school.average.dto.MateriaDTO;
import com.average.school.average.entity.Alumno;
import com.average.school.average.entity.Calificacion;

import java.util.List;

public interface AlumnoService {

    Alumno crearAlumno(AlumnoDTO alumnoDTO);

    Alumno agregarMaterias(Long alumnoId, List<MateriaDTO> materiasDTO);

    Calificacion agregarCalificacion(CalificacionDTO calificacionDTO);

    Double obtenerPromedioMateria(Long materiaId);

    Double obtenerPromedioMateriaPorBimestre(Long materiaId, int bimestre);

    Double obtenerPromedioAlumno(Long alumnoId);

    Double obtenerPromedioAlumnoPorBimestre(Long alumnoId, int bimestre);

    Calificacion modificarCalificacionPorBimestre(Long alumnoId, Long materiaId, int bimestre, double nuevaCalificacion);

    Alumno obtenerDetallesAlumno(Long alumnoId);

    Alumno actualizarAlumno(Long alumnoId, AlumnoDTO alumnoDTO);
}
