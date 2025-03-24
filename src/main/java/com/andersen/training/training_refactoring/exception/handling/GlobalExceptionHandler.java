package com.andersen.training.training_refactoring.exception.handling;

import com.andersen.training.training_refactoring.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Set<String> NONPROD_ENV_POSTFIX_SPECIFIERS = Set.of("dev", "local", "test");

    private final boolean includeNonProdDebugDetails;

    public GlobalExceptionHandler(Environment environment) {
        this.includeNonProdDebugDetails = Arrays.stream(environment.getActiveProfiles())
                .anyMatch(NONPROD_ENV_POSTFIX_SPECIFIERS::contains);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFoundException(NotFoundException notFoundException) {
        log.debug(notFoundException.getLocalizedMessage());

        ProblemDetail baseProblemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getSecureMessage());
        return enhanceProblemDetail(baseProblemDetail, notFoundException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        log.info(argumentNotValidException.getLocalizedMessage());

        ProblemDetail baseProblemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request body content failed validation");
        return enhanceProblemDetail(baseProblemDetail, argumentNotValidException);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleUnhandledRuntimeException(RuntimeException runtimeException) {
        log.error("Unhandled server exception", runtimeException);

        ProblemDetail baseProblemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unhandled server exception");
        return enhanceProblemDetail(baseProblemDetail, runtimeException);
    }

    private ProblemDetail enhanceProblemDetail(ProblemDetail baseProblemDetail, Throwable exception){
        if (includeNonProdDebugDetails) {
            ExtendedDebugProblemDetail extendedDebugProblemDetail = new ExtendedDebugProblemDetail(baseProblemDetail);
            extendedDebugProblemDetail.setTimestamp(OffsetDateTime.now());
            extendedDebugProblemDetail.setDetailedDebugMessage(exception.getLocalizedMessage());

            return extendedDebugProblemDetail;
        }

        return baseProblemDetail;
    }
}
