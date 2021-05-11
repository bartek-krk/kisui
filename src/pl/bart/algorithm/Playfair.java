package pl.bart.algorithm;

import pl.bart.common.AsciiConstants;
import pl.bart.exception.InvalidInputException;
import pl.bart.pojo.Coordinates;
import pl.bart.validator.InputValidator;

import java.util.*;

public class Playfair implements Encoder<Map<Character, Coordinates>> {

    public static final int SQUARE_SIZE = 8;

    private final Map<Character,Coordinates> alphabet;

    public Playfair() { this.alphabet = this.createAlphabet(); }

    @Override
    public String cipher(String input) throws InvalidInputException {
        InputValidator.validate(input); //throws InvalidInputException if not matching

        if (input.length() % 2 != 0) input = input + "_";

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<input.length(); i+=2) {
            Coordinates c1 = this.alphabet.get(input.charAt(i));
            Coordinates c2 = this.alphabet.get(input.charAt(i+1));

            if (c1.getX() == c2.getX()) {
                Coordinates newC1 = new Coordinates(c1.getX(), (c1.getY() + 1) % 8);
                Coordinates newC2 = new Coordinates(c2.getX(), (c2.getY() + 1) % 8);
                sb.append(findByCoordinates(this.alphabet, newC1));
                sb.append(findByCoordinates(this.alphabet, newC2));
            }

            else if (c1.getY() == c2.getY()) {
                Coordinates newC1 = new Coordinates((c1.getX()+1)%8, c1.getY());
                Coordinates newC2 = new Coordinates((c2.getX()+1)%8, c2.getY());
                sb.append(findByCoordinates(this.alphabet, newC1));
                sb.append(findByCoordinates(this.alphabet, newC2));
            }

            else {
                Coordinates newC1 = new Coordinates(c2.getX(), c1.getY());
                Coordinates newC2 = new Coordinates(c1.getX(),c2.getY());
                sb.append(findByCoordinates(this.alphabet, newC1));
                sb.append(findByCoordinates(this.alphabet, newC2));
            }
        }

        return sb.toString();
    }

    private Character findByCoordinates(Map<Character, Coordinates> map, Coordinates coordinates) {
        Set<Map.Entry<Character, Coordinates>> entrySet = map.entrySet();
        Character c = null;
        for (Map.Entry<Character, Coordinates> entry : entrySet) {
            if (entry.getValue().equals(coordinates)) {
                c = entry.getKey();
                break;
            }
        }
        if (c == null) throw new NoSuchElementException("Key not found");
        return c;
    }

    @Override
    public Map<Character, Coordinates> getCharacterMap() {
        return this.alphabet;
    }

    private Map<Character,Coordinates> createAlphabet() {
        List<Character> chars = new ArrayList<>();
        for(int i = AsciiConstants.LOWERCASE_START; i<=AsciiConstants.LOWERCASE_STOP; i++) chars.add((char)i);
        for(int i=AsciiConstants.UPPERCASE_START; i<=AsciiConstants.UPPERCASE_STOP; i++) chars.add((char)i);
        for(int i=AsciiConstants.DIGIT_START; i<=AsciiConstants.DIGIT_STOP; i++) chars.add((char)i);
        chars.add((char)AsciiConstants.WHITESPACE);
        chars.add((char)AsciiConstants.UNDERSCORE);

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
