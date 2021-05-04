package pl.bart.algorithm;

import pl.bart.common.AsciiConstants;
import pl.bart.exception.InvalidInputException;
import pl.bart.pojo.Coordinates;
import pl.bart.validator.InputValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolybiusSquare implements Encoder<Map<Character,Coordinates>> {

    public static final int SQUARE_SIZE = 8;

    private final Map<Character,Coordinates> alphabet;

    public PolybiusSquare() {
        this.alphabet = createAlphabet();
    }

    public Map<Character, Coordinates> getCharacterMap() {
        return alphabet;
    }

    @Override
    public String cipher(String input) throws InvalidInputException {
        StringBuilder sb = new StringBuilder();

        InputValidator.validate(input); //throws InvalidInputException if not matching

        for (char c : input.toCharArray()) {
            Coordinates coordinates = this.alphabet.get(c);
            String expression = String.format("%d%d", coordinates.getX(), coordinates.getY());
            sb.append(String.format("%s ", expression));
        }
        return sb.toString();
    }

    private Map<Character,Coordinates> createAlphabet() {
        List<Character> chars = new ArrayList<>();
        for(int i = AsciiConstants.LOWERCASE_START; i<=AsciiConstants.LOWERCASE_STOP; i++) chars.add((char)i);
        for(int i=AsciiConstants.UPPERCASE_START; i<=AsciiConstants.UPPERCASE_STOP; i++) chars.add((char)i);
        for(int i=AsciiConstants.DIGIT_START; i<=AsciiConstants.DIGIT_STOP; i++) chars.add((char)i);
        chars.add((char)AsciiConstants.WHITESPACE);

        Map<Character,Coordinates> alphabet = new HashMap<>();

        for (int x=0; x<SQUARE_SIZE; x++){
            for (int y=0; y<SQUARE_SIZE; y++) {
                int index = getRandomNumber(0, chars.size());
                char currentChar;
                if (!chars.isEmpty()) currentChar = chars.get(index);
                else break;
                alphabet.put(currentChar, new Coordinates(x,y));
                chars.remove(index);
            }
        }

        return alphabet;
    }
}
