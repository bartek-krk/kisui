package pl.bart.validator;

import pl.bart.exception.InvalidInputException;

import static pl.bart.exception.InvalidInputException.Reason;

public class InputValidator {
    public static boolean validate(String input) throws InvalidInputException {
        if (input.isEmpty()) throw new InvalidInputException(Reason.EMPTY_STRING);
        else if (! input.matches("[a-zA-Z0-9 ]+")) throw new InvalidInputException(Reason.INVALID_CHARACTERS);
        return true;
    }
}
