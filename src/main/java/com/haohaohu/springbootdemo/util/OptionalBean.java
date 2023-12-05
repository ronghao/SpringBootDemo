package com.haohaohu.springbootdemo.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class OptionalBean<T> {
    private static final OptionalBean<?> EMPTY = new OptionalBean();
    private final T value;

    public static <T> OptionalBean<T> empty() {
        OptionalBean<T> none = (OptionalBean<T>) EMPTY;
        return none;
    }

    private OptionalBean() {
        this.value = null;
    }

    private OptionalBean(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> OptionalBean<T> of(T value) {
        return new OptionalBean(value);
    }

    public static <T> OptionalBean<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        return this.value;
    }

    public <R> OptionalBean<R> getBean(Function<? super T, ? extends R> fn) {
        return Objects.isNull(this.value) ? empty() : ofNullable(fn.apply(this.value));
    }

    public T orElse(T other) {
        return ObjectUtil.defaultIfNull(this.value, other);
    }

    public T orElseGet(Supplier<? extends T> other) {
        return null != this.value ? this.value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return Assert.notNull(this.value, exceptionSupplier);
    }

    public boolean isPresent() {
        return null != this.value;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (this.value != null) {
            consumer.accept(this.value);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
