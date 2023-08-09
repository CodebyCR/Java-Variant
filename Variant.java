package de.semaino.damex.utils;

import java.util.Optional;
import java.util.function.Consumer;

public class Variant<T, U> {
    private T firstTypeValue;
    private U secondTypeValue;

    public void setFirstOf(T value) {
        firstTypeValue = value;
    }

    public void setSecondOf(U value) {
        secondTypeValue = value;
    }

    public Optional<T> getFirstTypeValue() {
        return Optional.ofNullable(firstTypeValue);
    }

    public Optional<U> getSecondTypeValue() {
        return Optional.ofNullable(secondTypeValue);
    }

    public void getFirsteOrSecond(Consumer<T> firstTypeAction, Consumer<U> secondTypeAction) {
        if(firstTypeValue == null && secondTypeValue == null) throw new NullPointerException("Trying to call an attribute that has not been set.");

        if (firstTypeValue != null) {
            firstTypeAction.accept(firstTypeValue);
        }
        else {
            secondTypeAction.accept(secondTypeValue);
        }
    }

    public static void main(String[] args) {
        Variant<String, Integer> variant = new Variant<>();
        variant.setFirstOf("Hello");
        variant.getFirsteOrSecond(
                (str) -> System.out.println("First type: " + str),
                (integer) ->{ integer = integer + 1; System.out.println("Second type: " + integer);}
        );

        variant.setSecondOf(1);
        variant.getFirsteOrSecond(
                (str) -> System.out.println("First type: " + str),
                (integer) ->{ integer = integer + 1; System.out.println("Second type: " + integer);}
        );

        variant.getFirstTypeValue().ifPresent(System.out::println);
    }
}

