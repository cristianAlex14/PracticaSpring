package com.average.school.average.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Punto de corte que abarca todos los métodos en el paquete de servicios
    @Pointcut("within(mx.com.citi.tu_proyecto.services..*)")
    public void serviceMethods() {}

    // Advice antes de la ejecución del método
    @Before("serviceMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        log.info("Entrando al método: {} - argumentos: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    // Advice después de la ejecución exitosa del método
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        log.info("Saliendo del método: {} - resultado: {}", joinPoint.getSignature(), result);
    }

    // Advice en caso de excepción
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        log.error("Excepción en el método: {} - causa: {}", joinPoint.getSignature(), exception.getMessage(), exception);
    }
}
