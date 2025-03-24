package com.andersen.training.training_refactoring.exception.handling;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ProblemDetail;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ExtendedDebugProblemDetail extends ProblemDetail {

    private OffsetDateTime timestamp;
    private String detailedDebugMessage;
    private String cause;

    public ExtendedDebugProblemDetail(ProblemDetail other) {
        super(other);
    }
}
