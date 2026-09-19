package com.workshop.store.application.textDecorator;

import com.workshop.store.application.validation.RuleBuilder;
import com.workshop.store.application.validation.RuleSet;

public final class DecorateCommandRuleset {
    private DecorateCommandRuleset() {
    }

    public static RuleSet<DecorateCommand, DecorateCommandValidationError> create() {

        return RuleBuilder.<DecorateCommand, DecorateCommandValidationError>create()
                .addRule(
                        command -> command.text() != null &&
                                !command.text().isEmpty(),

                        new DecorateCommandValidationError.EmptyText())
                .build();
    }
}