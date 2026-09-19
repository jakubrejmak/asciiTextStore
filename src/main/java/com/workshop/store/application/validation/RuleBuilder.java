package com.workshop.store.application.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RuleBuilder<T, E> {
    private final List<Rule<T, E>> rules = new ArrayList<>();

    private RuleBuilder() {
    }

    public static <T, E> RuleBuilder<T, E> create() {
        return new RuleBuilder<>();
    }

    public RuleBuilder<T, E> addRule(Predicate<T> rule, E violation) {
        this.rules.add(new Rule<T, E>(rule, violation));
        return this;
    }

    public RuleBuilder<T, E> addRuleIf(Predicate<T> condition, Predicate<T> rule, E violation) {
        this.rules.add(new Rule<T, E>(
                entity -> !condition.test(entity) || rule.test(entity),
                violation));
        return this;
    }

    public RuleSet<T, E> build() {
        return new RuleSet<>(this.rules);
    }
}
