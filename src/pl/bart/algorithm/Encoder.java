package pl.bart.algorithm;

import pl.bart.exception.InvalidInputException;

public interface Encoder extends AutoCloseable {
    public String cipher(String input) throws InvalidInputException;
}
