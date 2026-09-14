package com.workshop.store.infrastructure.http.textDecorator;

public record DecorateTextRequest(
        String text,
        char decorator) {
};
