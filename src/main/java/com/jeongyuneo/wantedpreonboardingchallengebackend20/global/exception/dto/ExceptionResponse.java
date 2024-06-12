package com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.dto;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationException;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationExceptionStatus;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record ExceptionResponse(
        String requestMethod,
        String requestUri,
        String code,
        String message,
        String timestamp
) {

    public static ExceptionResponse of(HttpServletRequest request, ApplicationException exception, String zoneId) {
        return new ExceptionResponse(
                request.getMethod(),
                request.getRequestURI(),
                exception.getCode(),
                exception.getMessage(),
                ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(zoneId)).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
    }

    public static ExceptionResponse of(HttpServletRequest request, ApplicationExceptionStatus exception, String zoneId) {
        return new ExceptionResponse(
                request.getMethod(),
                request.getRequestURI(),
                exception.getCode(),
                exception.getMessage(),
                ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(zoneId)).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
    }

    public static ExceptionResponse internalServerError(HttpServletRequest request, String zoneId) {
        return new ExceptionResponse(
                request.getMethod(),
                request.getRequestURI(),
                ApplicationExceptionStatus.INTERNAL_SERVER_ERROR.getCode(),
                ApplicationExceptionStatus.INTERNAL_SERVER_ERROR.getMessage(),
                ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(zoneId)).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
    }
}
