package com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.handler;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationException;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @Value("${zoneId}")
    private String zoneId;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionResponse> handleApplicationException(HttpServletRequest request, ApplicationException exception) {
        log.info("applicationException: {}", exception.getMessage(), exception);
        return ResponseEntity
                .internalServerError()
                .body(ExceptionResponse.of(request, exception, zoneId));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpServletRequest request, Exception exception) {
        log.info("exception: {}", exception.getMessage(), exception);
        return ResponseEntity
                .internalServerError()
                .body(ExceptionResponse.internalServerError(request, zoneId));
    }
}
