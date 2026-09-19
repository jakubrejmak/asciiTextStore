package com.workshop.store.application.validation;

import java.util.ArrayList;
import java.util.List;

public class RuleSet<T, E> {
    private final List<Rule<T, E>> rules;

    public RuleSet(List<Rule<T, E>> rules) {
        this.rules = List.copyOf(rules);
    }

    public List<E> validate(T entity) {
        List<E> errors = new ArrayList<>();

        for (Rule<T, E> rule : this.rules) {
            if (!rule.predicate().test(entity)) {
                errors.add(rule.violation());
            }
        }

        return List.copyOf(errors);
    }
}
