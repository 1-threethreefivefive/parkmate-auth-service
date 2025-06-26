package com.parkmate.authservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    // ✅ 2xx: 성공
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),
    CREATED(HttpStatus.CREATED, true, 201, "회원가입이 완료되었습니다."),
    LOGOUT_SUCCESS(HttpStatus.NO_CONTENT, true, 204, "로그아웃 되었습니다."),
    AUTH_EMAIL_SENT_AFTER_LOCK(HttpStatus.OK, true, 206, "계정이 잠금 처리되었으며, 이메일 알림을 전송했습니다."),

    // ❌ 4xx: 클라이언트 오류
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, false, 400, "잘못된 요청입니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, false, 400, "이메일 형식이 올바르지 않습니다."),
    INVALID_NAME_FORMAT(HttpStatus.BAD_REQUEST, false, 400, "이름은 2~50자 이내여야 합니다."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, false, 400, "비밀번호는 영문, 숫자, 특수문자를 포함한 8~20자여야 합니다."),
    INVALID_PHONE_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, false, 400, "전화번호는 010으로 시작하는 11자리 숫자여야 합니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, false, 400, "인증 코드는 6자리여야 합니다."),
    INVALID_ACCOUNT_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, false, 400, "계좌번호는 숫자와 '-'만 포함할 수 있으며 9~30자여야 합니다."),
    INVALID_BUSINESS_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, false, 400, "사업자등록번호는 숫자 10자리여야 합니다."),
    INVALID_SETTLEMENT_CYCLE(HttpStatus.BAD_REQUEST, false, 400, "정산 주기는 15일 또는 30일만 가능합니다."),
    INVALID_VERIFICATION_CODE_MISMATCH(HttpStatus.BAD_REQUEST, false, 400, "인증번호가 일치하지 않습니다."),
    VERIFICATION_CODE_ALREADY_SENT(HttpStatus.BAD_REQUEST, false, 400, "이미 인증코드가 발송되었습니다. 잠시 후 다시 시도해주세요."),

    // ❌ 401: 인증 오류
    INVALID_AUTH_PASSWORD(HttpStatus.UNAUTHORIZED, false, 401, "비밀번호가 일치하지 않습니다."),
    AUTH_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, false, 401, "이메일 인증에 실패하였습니다."),
    EMAIL_NOT_VERIFIED_HOST(HttpStatus.UNAUTHORIZED, false, 401, "호스트 이메일 인증이 완료되지 않았습니다."),
    EMAIL_NOT_VERIFIED(HttpStatus.UNAUTHORIZED, false, 401, "이메일 인증이 완료되지 않았습니다."),
    AUTH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, false, 401, "토큰이 만료되었습니다."),
    AUTH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, false, 401, "유효하지 않은 토큰입니다."),
    AUTH_LOGIN_FAILED_TOO_MANY_TIMES(HttpStatus.UNAUTHORIZED, false, 401, "로그인 시도 횟수가 초과되었습니다."),
    AUTH_VERIFICATION_CODE_NOT_FOUND(HttpStatus.UNAUTHORIZED, false, 401, "인증코드가 만료되었거나 존재하지 않습니다."),
    VERIFICATION_ATTEMPT_BLOCKED(HttpStatus.UNAUTHORIZED, false, 401, "인증코드 입력 시도 횟수를 초과했습니다. 잠시 후 다시 시도해주세요."),

    // ❌ 403: 권한 없음
    AUTH_FORBIDDEN(HttpStatus.FORBIDDEN, false, 403, "접근 권한이 없습니다."),

    // ❌ 404: 리소스 없음
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "리소스가 존재하지 않습니다."),

    // ✅ 비즈니스 오류 - HTTP OK로 처리하고 코드로 구분
    AUTH_USER_NOT_FOUND(HttpStatus.OK, false, 1001, "존재하지 않는 사용자입니다."),
    AUTH_HOST_NOT_FOUND(HttpStatus.OK, false, 1002, "존재하지 않는 호스트입니다."),
    AUTH_EMAIL_ALREADY_EXISTS(HttpStatus.OK, false, 1003, "이미 존재하는 이메일입니다."),
    AUTH_ACCOUNT_LOCKED(HttpStatus.OK, false, 1004, "로그인 실패가 누적되어 계정이 잠금 처리되었습니다. 이메일을 확인하세요."),
    AUTH_SOCIAL_PROVIDER_NOT_SUPPORTED(HttpStatus.OK, false, 1005, "지원하지 않는 소셜 로그인 제공자입니다."),
    AUTH_BUSINESS_NUMBER_INVALID(HttpStatus.OK, false, 1006, "유효하지 않은 사업자등록번호입니다."),

    // ❗ 5xx: 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "서버 내부 오류가 발생했습니다."),
    AUTH_USER_SERVICE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 501, "사용자 서비스 연동에 실패했습니다."),
    AUTH_USER_REGISTER_FAILED(HttpStatus.SERVICE_UNAVAILABLE, false, 502, "사용자 서비스에 회원 등록 요청이 실패했습니다."),
    AUTH_BIZNO_API_FAILED(HttpStatus.SERVICE_UNAVAILABLE, false, 503, "사업자등록번호 검증 서비스에 실패했습니다."),
    AUTH_LOCK_MAIL_FAILED(HttpStatus.SERVICE_UNAVAILABLE, false, 504, "계정 잠금 이메일 발송에 실패했습니다."),
    AUTH_HOST_SERVICE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 505, "호스트 서비스 연동에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}