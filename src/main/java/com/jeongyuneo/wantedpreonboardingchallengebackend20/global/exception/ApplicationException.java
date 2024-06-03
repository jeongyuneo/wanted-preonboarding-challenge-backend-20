package com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    public ApplicationException(ApplicationExceptionStatus status) {
        super(status.getMessage());
        this.status = status.getStatus();
        this.code = status.getCode();
    }
}
