# parkmate-auth-service

## 개요
**parkmate-auth-service**는 호스트와 사용자 인증을 담당하는 Spring Boot 기반의 인증 마이크로서비스입니다.  
클린 아키텍처를 기반으로, 도메인/애플리케이션/프레젠테이션/인프라 계층으로 분리되어 있습니다.

---

## 주요 기능
- 호스트/사용자 회원가입 및 로그인
- JWT 기반 인증 및 Spring Security 연동
- 계정 잠금/해제, 비밀번호 관리
- 사업자번호 검증(호스트)
- 이메일 인증 및 메일 발송
- Redis를 활용한 세션/토큰 관리
- Swagger(OpenAPI) 기반 API 문서 제공

---

## 프로젝트 구조

```
src/main/java/com/parkmate/authservice/
├── authhost/         # 호스트 인증 도메인
│   ├── application/      # 서비스 로직
│   ├── domain/           # 엔티티 및 도메인 모델
│   ├── dto/              # 데이터 전송 객체
│   ├── infrastructure/   # JPA, 외부 API 연동 등
│   ├── presentation/     # REST 컨트롤러
│   └── vo/               # Value Object
├── authuser/         # 사용자 인증 도메인 (구조 동일)
├── common/           # 공통 모듈 (보안, 예외, 메일, Redis 등)
└── AuthserviceApplication.java # 메인 클래스
```

---

## 기술 스택

- Java 17
- Spring Boot 3.4.x
- Spring Data JPA
- Spring Security, JWT
- Spring Cloud (Eureka, OpenFeign)
- Redis
- MySQL
- Lombok
- Swagger (springdoc-openapi)
- JUnit, Spring Security Test

---

## 환경 변수 예시

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/parkmate
    username: root
    password: yourpassword
  redis:
    host: localhost
    port: 6379
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-mail-password
jwt:
  secret: your-jwt-secret-key
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

## 빌드 및 실행 방법

1. **환경 변수 설정**
   - DB, Redis, 메일 등 외부 서비스 연결 정보를 `application.yml` 또는 환경 변수로 설정

2. **의존성 설치 및 빌드**
   ```bash
   ./gradlew build
   ```

3. **실행**
   ```bash
   ./gradlew bootRun
   ```

4. **API 문서**
   - Swagger UI: `/swagger-ui/index.html` 접속

---

## 주요 API 예시

### 회원가입 (호스트)
- **POST** `/api/auth/host/signup`
- Request:
  ```json
  {
    "email": "host@example.com",
    "password": "1234",
    "bizNo": "123-45-67890"
  }
  ```
- Response:
  ```json
  {
    "hostUuid": "UUID값",
    "email": "host@example.com"
  }
  ```

### 로그인 (공통)
- **POST** `/api/auth/login`
- Request:
  ```json
  {
    "email": "user@example.com",
    "password": "1234"
  }
  ```
- Response:
  ```json
  {
    "accessToken": "JWT_ACCESS_TOKEN",
    "refreshToken": "JWT_REFRESH_TOKEN"
  }
  ```

---

## 인증 흐름 예시

1. 회원가입 → 이메일 인증(선택) → 로그인
2. 로그인 성공 시 JWT 발급(Access/Refresh)
3. 인증이 필요한 API 호출 시 JWT AccessToken 사용
4. AccessToken 만료 시 RefreshToken으로 재발급
5. 로그아웃 시 RefreshToken 폐기

---

## 폴더별 상세 설명

- **authhost/application**: 호스트 인증 서비스, 비즈니스 로직
- **authhost/domain**: 호스트 엔티티, JPA 매핑, 도메인 정책
- **authhost/dto**: 요청/응답 DTO, 계층간 데이터 전달
- **authhost/infrastructure**: JPA Repository, 외부 API 연동(Feign 등)
- **authhost/presentation**: REST API Controller
- **authhost/vo**: 값 객체(Value Object), 불변 데이터 구조

- **authuser/**: 사용자 인증 도메인(구조 동일)
- **common/config**: Spring Security, Swagger, Redis 등 설정
- **common/exception**: 커스텀 예외, 글로벌 예외 핸들러
- **common/redis**: Redis 관련 유틸/설정
- **common/mail**: 메일 발송 서비스

---

## ERD/DB 구조 예시

- **auth_host**
  - id (PK)
  - host_uuid (UUID, Unique)
  - email (Unique)
  - password
  - account_locked (boolean)
  - created_at, updated_at

- **auth_user**
  - id (PK)
  - user_uuid (UUID, Unique)
  - email (Unique)
  - password
  - account_locked (boolean)
  - created_at, updated_at

---

## 외부 연동 및 정책

- **Eureka**: 서비스 디스커버리 클라이언트로 등록
- **OpenFeign**: 외부 API(사업자번호 검증 등) 연동
- **Redis**: 인증 토큰/세션 관리
- **메일**: 인증 메일, 비밀번호 찾기 등

---

## 테스트

- JUnit 기반 단위/통합 테스트
- Spring Security 테스트 지원

---

## 배포/운영

- Docker, docker-compose 등 컨테이너 환경에서 구동 가능
- CI/CD(Github Actions, Jenkins 등) 연동 시 환경 변수 관리 필요
- 운영 환경에서는 JWT 시크릿, DB/Redis/메일 비밀번호 등 민감 정보는 환경 변수로만 관리 권장

---

## 기타

- Swagger를 통한 API 문서 자동화
- 클린 아키텍처 기반의 계층 분리로 유지보수 용이
