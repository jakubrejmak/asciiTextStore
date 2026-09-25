package com.workshop.store.application.textDecorator;

public record DecorateCommand(
        String text,
        int decoratorLineLength,
        char decorateWith,
        boolean centerText,
        boolean multiline) {
};
