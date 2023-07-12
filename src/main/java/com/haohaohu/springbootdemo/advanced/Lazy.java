package com.haohaohu.springbootdemo.advanced;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/4/13 19:33
 * @version v1.0
 */
public class Lazy<T> implements Supplier<T> {

    private final Supplier<? extends T> supplier;
    private T value;

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    public T get() {
        if (value == null) {
            T newValue = supplier.get();

            if (newValue == null) {
                throw new IllegalStateException("Lazy value can not be null!");
            }

            value = newValue;
        }

        return value;
    }

    public <S> Lazy<S> map(Function<? super T, ? extends S> function) {
        return Lazy.of(() -> function.apply(get()));
    }

    public <S> Lazy<S> flatMap(Function<? super T, Lazy<? extends S>> function) {
        return Lazy.of(() -> function.apply(get()).get());
    }

    /**
     * 反序列化
     */
    public static class LazyFormatSerializer implements ObjectSerializer {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            if (object instanceof Lazy) {
                Object o1 = ((Lazy) object).get();
                if (o1 != null) {
                    String jsonString = JSON.toJSONString(o1);
                    serializer.write(jsonString);
                    return;
                }
            }
            serializer.write(object);
        }
    }

    public static class LazyFormat implements ObjectDeserializer {
        @Override
        public String deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
            if (o instanceof Lazy) {
                Object o1 = ((Lazy) o).get();
                return JSON.toJSONString(o1);
            }
            return null;
        }

        @Override
        public int getFastMatchToken() {
            return 0;
        }
    }
}
