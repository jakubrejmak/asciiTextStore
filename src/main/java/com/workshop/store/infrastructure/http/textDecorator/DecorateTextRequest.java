package com.workshop.store.infrastructure.http.textDecorator;

import com.workshop.store.application.textDecorator.DecorateCommand;

public record DecorateTextRequest(
        String text,
        char decorator) {

    public DecorateCommand toCommand() {
        return new DecorateCommand(text, text.length(), decorator, true);
    }
};
