package com.workshop.store.application.textDecorator;

import java.util.Arrays;

import com.workshop.store.application.Exporter;

public class TextDecoratorService {
    private Exporter exporter;

    public TextDecoratorService(Exporter exporter) {
        this.exporter = exporter;
    }

    public String decorate(DecorateCommand command) {
        String[] parts = TextDecorator.partition(command.text(), '\n', command.decoratorLineLength());

        if (command.centerText()) {
            String[] centeredParts = Arrays.stream(parts)
                    .map(str -> TextDecorator.centerText(
                            str,
                            command.decoratorLineLength()))
                    .toArray(String[]::new);
            parts = centeredParts;
        }

        String line = String.join("\n", parts);

        this.exporter.export(line);

        return TextDecorator.decorate(line, command.decorateWith());
    }
}
