package pl.bart.validator;

import pl.bart.exception.InvalidInputException;

public class InputValidator {
    public static boolean validate(String input) throws InvalidInputException {
        if (input.matches("[a-zA-Z0-9 ]+")) return true;
        else throw new InvalidInputException();
    }
}
