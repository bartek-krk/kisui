package pl.bart.algorithm;

import pl.bart.exception.InvalidInputException;

import java.util.Map;

public interface Encoder<T extends Map<?,?>> extends AutoCloseable {
    public String cipher(String input) throws InvalidInputException;

    T getCharacterMap();

    @Override
    default void close() throws Exception {
        //pass
    }

    default int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
