package com.workshop.store.application.textDecorator;

public sealed interface TextDecoratorError {
    public String code();

    public String message();

    public record EmptyTextError() implements TextDecoratorError {
        @Override
        public String code() {
            return "empty_text";
        }

        @Override
        public String message() {
            return "Text field is empty";
        }
    };

    public record InvalidLineLength(int length) implements TextDecoratorError {
        @Override
        public String code() {
            return "invalid_line_length";
        }

        @Override
        public String message() {
            return "The length: " + this.length + " is not valid";
        }
    }
}
