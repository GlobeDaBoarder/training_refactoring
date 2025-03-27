package com.andersen.training.training_refactoring.exception.handling;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ProblemDetail;

import java.time.OffsetDateTime;

/// Extended version of ProblemDetail to include additional debugging information.
/// This class extends the ProblemDetail class and adds fields for timestamp,
/// detailed debug message, and cause.
///
/// @author Gleb
/// @since 2.0.0
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
