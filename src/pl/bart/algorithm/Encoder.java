package pl.bart.algorithm;

import pl.bart.exception.InvalidInputException;

public interface Encoder {
    public String cipher(String input) throws InvalidInputException;
}
