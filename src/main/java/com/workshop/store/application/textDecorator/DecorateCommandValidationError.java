package com.workshop.store.application.textDecorator;

import com.workshop.store.application.ApplicationError;

public sealed interface DecorateCommandValidationError extends ApplicationError {

    record EmptyText()
            implements DecorateCommandValidationError {

        @Override
        public String code() {
            return "empty_text";
        }

        @Override
        public String message() {
            return "Text field is empty";
        }
    }

    record InvalidLineLength(int currentLength, int minimalLength)
            implements DecorateCommandValidationError {

        @Override
        public String code() {
            return "invalid_line_length";
        }

        @Override
        public String message() {
            return "Line length "
                    + currentLength
                    + " is invalid. Length must be greater than "
                    + minimalLength;
        }
    }
}
