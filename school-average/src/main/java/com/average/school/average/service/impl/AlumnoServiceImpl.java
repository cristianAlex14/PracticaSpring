package com.average.school.average.service.impl;

import com.average.school.average.dto.AlumnoDTO;
import com.average.school.average.dto.CalificacionDTO;
import com.average.school.average.dto.MateriaDTO;
import com.average.school.average.entity.Alumno;
import com.average.school.average.entity.Calificacion;
import com.average.school.average.entity.Materia;
import com.average.school.average.exceptions.ResourceNotFoundException;
import com.average.school.average.repository.AlumnoRepository;
import com.average.school.average.repository.CalificacionRepository;
import com.average.school.average.repository.MateriaRepository;
import com.average.school.average.service.AlumnoService;
import lombok.extern.slf4j.Slf4j; // Import necesario para @Slf4j
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public Alumno crearAlumno(AlumnoDTO alumnoDTO) {
        log.debug("Creando nuevo alumno con nombre: {}", alumnoDTO.getNombre());
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDTO.getNombre());
        Alumno alumnoGuardado = alumnoRepository.save(alumno);
        log.debug("Alumno guardado: {}", alumnoGuardado);
        return alumnoGuardado;
    }

    @Override
    public Alumno agregarMaterias(Long alumnoId, List<MateriaDTO> materiasDTO) {
        log.info("Entrando a agregarMaterias - alumnoId: {}, materiasDTO: {}", alumnoId, materiasDTO);
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> {
                    log.error("Alumno con ID {} no encontrado", alumnoId);
                    return new RuntimeException("Alumno no encontrado");
                });

        for (MateriaDTO materiaDTO : materiasDTO) {
            Materia materia = new Materia();
            materia.setNombre(materiaDTO.getNombre());
            materiaRepository.save(materia);
            log.debug("Materia guardada: {}", materia);
        }
        log.info("Materias agregadas correctamente al alumno con ID {}", alumnoId);
        return alumno;
    }

    @Override
    public Calificacion agregarCalificacion(CalificacionDTO calificacionDTO) {
        log.info("Entrando a agregarCalificacion - calificacionDTO: {}", calificacionDTO);
        Alumno alumno = alumnoRepository.findById(calificacionDTO.getAlumnoId())
                .orElseThrow(() -> {
                    log.error("Alumno con ID {} no encontrado", calificacionDTO.getAlumnoId());
                    return new RuntimeException("Alumno no encontrado");
                });
        Materia materia = materiaRepository.findById(calificacionDTO.getMateriaId())
                .orElseThrow(() -> {
                    log.error("Materia con ID {} no encontrada", calificacionDTO.getMateriaId());
                    return new RuntimeException("Materia no encontrada");
                });

        Calificacion calificacion = new Calificacion();
        calificacion.setAlumno(alumno);
        calificacion.setMateria(materia);
        calificacion.setBimestre(calificacionDTO.getBimestre());
        calificacion.setCalificacion(calificacionDTO.getCalificacion());

        Calificacion savedCalificacion = calificacionRepository.save(calificacion);
        log.info("Calificación agregada correctamente: {}", savedCalificacion);
        return savedCalificacion;
    }

    @Override
    public Double obtenerPromedioMateria(Long materiaId) {
        log.info("Entrando a obtenerPromedioMateria - materiaId: {}", materiaId);
        Double promedio = calificacionRepository.calcularPromedioMateria(materiaId);
        log.info("Promedio de la materia con ID {}: {}", materiaId, promedio);
        return promedio;
    }

    @Override
    public Double obtenerPromedioMateriaPorBimestre(Long materiaId, int bimestre) {
        log.info("Entrando a obtenerPromedioMateriaPorBimestre - materiaId: {}, bimestre: {}", materiaId, bimestre);
        Double promedio = calificacionRepository.calcularPromedioMateriaPorBimestre(materiaId, bimestre);
        log.info("Promedio de la materia con ID {} para el bimestre {}: {}", materiaId, bimestre, promedio);
        return promedio;
    }

    @Override
    public Double obtenerPromedioAlumno(Long alumnoId) {
        log.info("Entrando a obtenerPromedioAlumno - alumnoId: {}", alumnoId);
        Double promedio = calificacionRepository.calcularPromedioAlumno(alumnoId);
        log.info("Promedio del alumno con ID {}: {}", alumnoId, promedio);
        return promedio;
    }

    @Override
    public Double obtenerPromedioAlumnoPorBimestre(Long alumnoId, int bimestre) {
        log.info("Entrando a obtenerPromedioAlumnoPorBimestre - alumnoId: {}, bimestre: {}", alumnoId, bimestre);
        Double promedio = calificacionRepository.calcularPromedioAlumnoPorBimestre(alumnoId, bimestre);
        log.info("Promedio del alumno con ID {} para el bimestre {}: {}", alumnoId, bimestre, promedio);
        return promedio;
    }

    @Override
    public Calificacion modificarCalificacionPorBimestre(Long alumnoId, Long materiaId, int bimestre, double nuevaCalificacion) {
        log.info("Entrando a modificarCalificacionPorBimestre - alumnoId: {}, materiaId: {}, bimestre: {}, nuevaCalificacion: {}",
                alumnoId, materiaId, bimestre, nuevaCalificacion);
        Calificacion calificacion = calificacionRepository.findByAlumnoIdAndMateriaId(alumnoId, materiaId).stream()
                .filter(c -> c.getBimestre() == bimestre)
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Calificación no encontrada para alumnoId: {}, materiaId: {}, bimestre: {}", alumnoId, materiaId, bimestre);
                    return new RuntimeException("Calificación no encontrada para el bimestre");
                });

        calificacion.setCalificacion(nuevaCalificacion);
        Calificacion updatedCalificacion = calificacionRepository.save(calificacion);
        log.info("Calificación actualizada: {}", updatedCalificacion);
        return updatedCalificacion;
    }

    @Override
    public Alumno obtenerDetallesAlumno(Long alumnoId) {
        log.debug("Buscando alumno con ID: {}", alumnoId);
        return alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno con ID " + alumnoId + " no encontrado"));
    }

    @Override
    public Alumno actualizarAlumno(Long alumnoId, AlumnoDTO alumnoDTO) {
        log.debug("Actualizando alumno con ID: {}", alumnoId);
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno con ID " + alumnoId + " no encontrado"));

        alumno.setNombre(alumnoDTO.getNombre());

        Alumno alumnoActualizado = alumnoRepository.save(alumno);
        log.debug("Alumno actualizado: {}", alumnoActualizado);
        return alumnoActualizado;
    }
}

