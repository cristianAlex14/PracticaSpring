package com.average.school.average.controller;

import com.average.school.average.dto.ResponseDTO;
import com.average.school.average.exceptions.ResourceNotFoundException;
import com.average.school.average.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDTO<Void> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseUtil.error("Recurso no encontrado", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO<Void> handleGeneralException(Exception ex) {
        return ResponseUtil.error("Error Interno", "Ha ocurrido un error interno en el servidor");
    }
}
