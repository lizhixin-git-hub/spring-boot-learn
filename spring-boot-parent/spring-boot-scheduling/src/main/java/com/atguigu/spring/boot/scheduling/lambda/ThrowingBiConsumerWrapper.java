package com.atguigu.spring.boot.scheduling.lambda;

import java.util.function.BiConsumer;

//https://www.helplib.com/GitHub/article_135646
public class ThrowingBiConsumerWrapper {

    public static <T, U, E extends Exception> BiConsumer<T, U> handlingBiConsumerWrapper(ThrowingConsumer<T, U, E> throwingConsumer, Class<E> exceptionClass) {
        return (arg1, arg2) -> {
            try {
                throwingConsumer.accept(arg1, arg2);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    System.err.println("Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    @FunctionalInterface
    public interface ThrowingConsumer<T, U, E extends Throwable> {
        void accept(T var1, U var2) throws E;
    }

}
