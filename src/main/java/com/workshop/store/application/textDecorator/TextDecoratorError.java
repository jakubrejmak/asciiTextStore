package com.workshop.store.application.textDecorator;

import java.util.List;

import com.workshop.store.application.ApplicationError;

public sealed interface TextDecoratorError extends ApplicationError {

    public record Validation(List<DecorateCommandValidationError> validationErrors)
            implements TextDecoratorError {

        public Validation(List<DecorateCommandValidationError> validationErrors) {
            this.validationErrors = List.copyOf(validationErrors);
        }

        @Override
        public String code() {
            return "validation";
        }

        @Override
        public String message() {
            String[] errors = this.validationErrors()
                    .stream()
                    .map(e -> e.message())
                    .toArray(String[]::new);

            return errors.length + " validation errors occured: \n"
                    + String.join("\n", errors);
        }

    }

    public record Export() implements TextDecoratorError {
        @Override
        public String code() {
            return "export_error";
        }

        @Override
        public String message() {
            return "Export failed";
        }
    }
}