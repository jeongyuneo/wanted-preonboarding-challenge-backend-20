package com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationExceptionStatus {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "600", "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "601", "허용되지 않은 요청입니다."),
    UNSUPPORTED_CONTENT_TYPE(HttpStatus.BAD_REQUEST, "602", "지원하지 않는 컨텐트 타입입니다."),

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "700", "회원을 찾을 수 없습니다."),

    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "800", "제품을 찾을 수 없습니다."),

    TRANSACTION_HISTORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "900", "거래 내역을 찾을 수 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "1000", "서버 내부 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
