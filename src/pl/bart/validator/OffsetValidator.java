package pl.bart.validator;

import pl.bart.exception.InvalidOffsetException;

public class OffsetValidator {
    public static boolean validate(String input) throws InvalidOffsetException{
        if (input.matches("[0-9]+")){
            int number = Integer.parseInt(input);
            if (number >= 0) return true;
            else throw new InvalidOffsetException();
        } else throw new InvalidOffsetException();
    }
}
