package com.suiteonix.schoolpaz.kernel.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ControllerAdvice(basePackages = {"com.suiteonix"})
@RequiredArgsConstructor
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Data integrity violation: " + e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    ProblemDetail handleBadCredentialsException(BadCredentialsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    @ExceptionHandler(AccessDeniedException.class)
    ProblemDetail handleAccessDeniedException(AccessDeniedException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Access denied");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {

//        Stream<ErrorResponseDto.Error> errors = ex.getBindingResult().getAllErrors().stream().map((error) -> ErrorResponseDto.Error.FromFieldError((FieldError) error.));
//        ex.getBindingResult().getFieldErrors().forEach(error -> {})
        if (ex.getBody().getProperties() == null)
            ex.getBody().setProperties(new HashMap<>());
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                ex.getBody().getProperties().put(fieldError.getField(), fieldError.getDefaultMessage()));
        ex.getBody().getProperties().put(
                "fields",
                ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                        Map.of(fieldError.getField(), fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : ""))
        );
        return new ResponseEntity<>(ex.getBody(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        if (problem.getProperties() == null)
            problem.setProperties(new HashMap<>());
        if (ex.getCause() instanceof InvalidFormatException ife) {
            problem.getProperties().put("field", ife.getPath().getLast().getFieldName());
            problem.getProperties().put("value", ife.getValue());
            problem.getProperties().put("type", ife.getTargetType().getSimpleName());
        }
        return new ResponseEntity<>(problem, status);
    }


    @ExceptionHandler(Exception.class)
    ProblemDetail handleAllUncaughtException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
    }

    @ExceptionHandler(PazException.class)
    ProblemDetail handleNixException(PazException e) {
        return e.toProblemDetail();
    }

}
