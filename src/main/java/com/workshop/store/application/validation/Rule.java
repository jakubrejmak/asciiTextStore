package com.workshop.store.application.validation;

import java.util.function.Predicate;

public record Rule<T, E>(
        Predicate<T> predicate,
        E violation) {
}
