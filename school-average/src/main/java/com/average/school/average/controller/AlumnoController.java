package com.average.school.average.controller;

import com.average.school.average.dto.AlumnoDTO;
import com.average.school.average.dto.CalificacionDTO;
import com.average.school.average.dto.ModificarCalificacionDTO;
import com.average.school.average.dto.MateriaDTO;
import com.average.school.average.dto.ResponseDTO;
import com.average.school.average.entity.Alumno;
import com.average.school.average.entity.Calificacion;
import com.average.school.average.service.AlumnoService;
import com.average.school.average.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j; // Import necesario para @Slf4j
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Operation(summary = "Crear un nuevo alumno")
    @ApiResponse(responseCode = "201", description = "Alumno creado correctamente")
    @PostMapping
    public ResponseDTO<Alumno> crearAlumno(@RequestBody @Valid AlumnoDTO alumnoDTO) {
        log.info("Recibida solicitud para crear un nuevo alumno: {}", alumnoDTO);
        Alumno nuevoAlumno = alumnoService.crearAlumno(alumnoDTO);
        log.info("Alumno creado correctamente: {}", nuevoAlumno);
        return ResponseUtil.success(nuevoAlumno, "Alumno creado correctamente");
    }

    @Operation(summary = "Obtener detalles de un alumno")
    @ApiResponse(responseCode = "200", description = "Detalles del alumno obtenidos correctamente")
    @GetMapping("/{alumnoId}")
    public ResponseDTO<Alumno> obtenerDetallesAlumno(@PathVariable Long alumnoId) {
        log.info("Recibida solicitud para obtener detalles del alumno con ID {}", alumnoId);
        Alumno alumno = alumnoService.obtenerDetallesAlumno(alumnoId);
        log.info("Detalles del alumno obtenidos correctamente: {}", alumno);
        return ResponseUtil.success(alumno, "Detalles del alumno obtenidos correctamente");
    }

    @Operation(summary = "Actualizar información de un alumno")
    @ApiResponse(responseCode = "200", description = "Alumno actualizado correctamente")
    @PutMapping("/{alumnoId}")
    public ResponseDTO<Alumno> actualizarAlumno(
            @PathVariable Long alumnoId,
            @RequestBody @Valid AlumnoDTO alumnoDTO) {
        log.info("Recibida solicitud para actualizar el alumno con ID {}: {}", alumnoId, alumnoDTO);
        Alumno alumnoActualizado = alumnoService.actualizarAlumno(alumnoId, alumnoDTO);
        log.info("Alumno actualizado correctamente: {}", alumnoActualizado);
        return ResponseUtil.success(alumnoActualizado, "Alumno actualizado correctamente");
    }

    @Operation(summary = "Agregar una lista de materias por alumno")
    @ApiResponse(responseCode = "200", description = "Materias agregadas correctamente")
    @PostMapping("/{alumnoId}/materias")
    public ResponseDTO<Alumno> agregarMaterias(
            @PathVariable Long alumnoId,
            @RequestBody @Valid List<MateriaDTO> materiasDTO) {
        log.info("Recibida solicitud para agregar materias al alumno con ID {}", alumnoId);
        Alumno alumno = alumnoService.agregarMaterias(alumnoId, materiasDTO);
        log.info("Materias agregadas correctamente al alumno con ID {}", alumnoId);
        return ResponseUtil.success(alumno, "Materias agregadas correctamente");
    }

    @Operation(summary = "Agregar la calificación de una materia para un alumno")
    @ApiResponse(responseCode = "200", description = "Calificación agregada correctamente")
    @PostMapping("/calificaciones")
    public ResponseDTO<Calificacion> agregarCalificacion(@RequestBody @Valid CalificacionDTO calificacionDTO) {
        log.info("Recibida solicitud para agregar calificación: {}", calificacionDTO);
        Calificacion calificacion = alumnoService.agregarCalificacion(calificacionDTO);
        log.info("Calificación agregada correctamente: {}", calificacion);
        return ResponseUtil.success(calificacion, "Calificación agregada correctamente");
    }

    @Operation(summary = "Mostrar el promedio de una materia")
    @ApiResponse(responseCode = "200", description = "Promedio obtenido correctamente")
    @GetMapping("/materias/{materiaId}/promedio")
    public ResponseDTO<Double> obtenerPromedioMateria(@PathVariable Long materiaId) {
        log.info("Recibida solicitud para obtener el promedio de la materia con ID {}", materiaId);
        Double promedio = alumnoService.obtenerPromedioMateria(materiaId);
        log.info("Promedio obtenido para la materia con ID {}: {}", materiaId, promedio);
        return ResponseUtil.success(promedio, "Promedio obtenido correctamente");
    }

    @Operation(summary = "Mostrar el promedio de una materia por bimestre")
    @ApiResponse(responseCode = "200", description = "Promedio por bimestre obtenido correctamente")
    @GetMapping("/materias/{materiaId}/promedio/bimestre/{bimestre}")
    public ResponseDTO<Double> obtenerPromedioMateriaPorBimestre(
            @PathVariable Long materiaId,
            @PathVariable int bimestre) {
        log.info("Recibida solicitud para obtener el promedio de la materia con ID {} para el bimestre {}", materiaId, bimestre);
        Double promedio = alumnoService.obtenerPromedioMateriaPorBimestre(materiaId, bimestre);
        log.info("Promedio obtenido: {}", promedio);
        return ResponseUtil.success(promedio, "Promedio por bimestre obtenido correctamente");
    }

    @Operation(summary = "Mostrar el promedio de todas las materias de un alumno")
    @ApiResponse(responseCode = "200", description = "Promedio obtenido correctamente")
    @GetMapping("/{alumnoId}/promedio")
    public ResponseDTO<Double> obtenerPromedioAlumno(@PathVariable Long alumnoId) {
        log.info("Recibida solicitud para obtener el promedio del alumno con ID {}", alumnoId);
        Double promedio = alumnoService.obtenerPromedioAlumno(alumnoId);
        log.info("Promedio obtenido para el alumno con ID {}: {}", alumnoId, promedio);
        return ResponseUtil.success(promedio, "Promedio obtenido correctamente");
    }

    // Endpoint para obtener el promedio de todas las materias de un alumno por bimestre
    @Operation(summary = "Mostrar el promedio de todas las materias de un alumno por bimestre")
    @ApiResponse(responseCode = "200", description = "Promedio por bimestre obtenido correctamente")
    @GetMapping("/{alumnoId}/promedio/bimestre/{bimestre}")
    public ResponseDTO<Double> obtenerPromedioAlumnoPorBimestre(
            @PathVariable Long alumnoId,
            @PathVariable int bimestre) {
        log.info("Recibida solicitud para obtener el promedio del alumno con ID {} para el bimestre {}", alumnoId, bimestre);
        Double promedio = alumnoService.obtenerPromedioAlumnoPorBimestre(alumnoId, bimestre);
        log.info("Promedio obtenido: {}", promedio);
        return ResponseUtil.success(promedio, "Promedio por bimestre obtenido correctamente");
    }

    @Operation(summary = "Modificar la calificación de una materia de un alumno por bimestre")
    @ApiResponse(responseCode = "200", description = "Calificación modificada correctamente")
    @PatchMapping("/{alumnoId}/materias/{materiaId}/calificaciones/bimestre/{bimestre}")
    public ResponseDTO<Calificacion> modificarCalificacionPorBimestre(
            @PathVariable Long alumnoId,
            @PathVariable Long materiaId,
            @PathVariable int bimestre,
            @RequestBody @Valid ModificarCalificacionDTO modificarCalificacionDTO) {
        Double nuevaCalificacion = modificarCalificacionDTO.getNuevaCalificacion();
        log.info("Recibida solicitud para modificar calificación - alumnoId: {}, materiaId: {}, bimestre: {}, nuevaCalificacion: {}",
                alumnoId, materiaId, bimestre, nuevaCalificacion);
        Calificacion calificacion = alumnoService.modificarCalificacionPorBimestre(
                alumnoId, materiaId, bimestre, nuevaCalificacion);
        log.info("Calificación modificada correctamente: {}", calificacion);
        return ResponseUtil.success(calificacion, "Calificación modificada correctamente");
    }
}
