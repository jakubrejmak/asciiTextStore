package com.workshop.store.application.textDecorator;

import java.util.Arrays;
import java.util.List;

import com.workshop.store.application.Result;
import com.workshop.store.application.export.Exporter;
import com.workshop.store.application.export.ExporterException;

public class TextDecoratorService {
    private Exporter exporter;

    public TextDecoratorService(Exporter exporter) {
        this.exporter = exporter;
    }

    public Result<String, TextDecoratorError> decorate(DecorateCommand command) {

        List<DecorateCommandValidationError> errors = DecorateCommandRuleset.create().validate(command);
        if (!errors.isEmpty()) {
            return Result.failure(new TextDecoratorError.Validation(errors));
        }

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

        String decorated = TextDecorator.decorate(line, command.decorateWith(), command.multiline());

        try {
            this.exporter.export(decorated);
        } catch (ExporterException e) {
            return Result.failure(new TextDecoratorError.Export());
        }

        return Result.success(decorated);
    }
}
