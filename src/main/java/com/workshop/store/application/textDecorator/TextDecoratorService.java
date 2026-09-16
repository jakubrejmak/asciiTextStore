package com.workshop.store.application.textDecorator;

import java.util.Arrays;

import com.workshop.store.application.Result;
import com.workshop.store.application.export.Exporter;

public class TextDecoratorService {
    private Exporter exporter;

    public TextDecoratorService(Exporter exporter) {
        this.exporter = exporter;
    }

    public Result<String, TextDecoratorError> decorate(DecorateCommand command) {
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

        return Result.success(TextDecorator.decorate(line, command.decorateWith()));
    }
}
