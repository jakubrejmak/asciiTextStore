package com.workshop.store.infrastructure.http.textDecorator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.store.application.Result;
import com.workshop.store.application.textDecorator.TextDecoratorError;
import com.workshop.store.application.textDecorator.TextDecoratorService;

@RestController
public class DecorateTextController {
    private ResponseEntity<ProblemDetail> errorResponse(TextDecoratorError error) {
        HttpStatus status = switch (error) {
            case TextDecoratorError.Validation ignored ->
                HttpStatus.BAD_REQUEST;

            case TextDecoratorError.Export ignored ->
                HttpStatus.INTERNAL_SERVER_ERROR;
        };

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                error.message());
        problem.setTitle("Text decoration failed");
        problem.setProperty("code", error.code());

        return ResponseEntity.status(status).body(problem);
    }

    @PostMapping("/api/v1/decorate")
    public ResponseEntity<?> decorateText(
            TextDecoratorService service,
            @RequestBody DecorateTextRequest request) {
        return switch (service.decorate(request.toCommand())) {
            case Result.Success<String, TextDecoratorError> success ->
                ResponseEntity.ok(new DecorateTextResponse(success.value()));

            case Result.Failure<String, TextDecoratorError> failure ->
                errorResponse(failure.error());
        };
    }
}
