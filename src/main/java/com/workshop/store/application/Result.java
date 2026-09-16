package com.workshop.store.application;

public sealed interface Result<T, E>
        permits Result.Success, Result.Failure {
    record Success<T, E>(T value) implements Result<T, E> {
    }

    record Failure<T, E>(E error) implements Result<T, E> {
    }

    static <T, E> Result<T, E> success(T value) {
        return new Result.Success<>(value);
    }

    static <T, E> Result<T, E> failure(E error) {
        return new Result.Failure<>(error);
    }
}