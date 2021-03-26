package pl.bart.algorithm;

import pl.bart.common.AsciiConstants;
import pl.bart.exception.InvalidInputException;
import pl.bart.validator.InputValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomPermutation implements Encoder{
    private final Map<Character,Character> characterMap;

    public RandomPermutation(){
        this.characterMap = createAlphabet();
    }

    @Override
    public String cipher(String input) throws InvalidInputException {
        StringBuilder stringBuilder = new StringBuilder();
        if (InputValidator.validate(input)) for(char c : input.toCharArray()) stringBuilder.append(characterMap.get(c));
        return stringBuilder.toString();
    }

    public Map<Character,Character> getCharacterMap(){
        return new HashMap<>(this.characterMap);
    }

    private Map<Character,Character> createAlphabet(){
        List<Character> chars = new ArrayList<>();
        for(int i=AsciiConstants.LOWERCASE_START; i<=AsciiConstants.LOWERCASE_STOP; i++) chars.add((char)i);
        for(int i=AsciiConstants.UPPERCASE_START; i<=AsciiConstants.UPPERCASE_STOP; i++) chars.add((char)i);
        for(int i=AsciiConstants.DIGIT_START; i<=AsciiConstants.DIGIT_STOP; i++) chars.add((char)i);
        chars.add((char)AsciiConstants.WHITESPACE);

        Map<Character,Character> alphabet = new HashMap<>();
        chars.forEach(c -> alphabet.put(c,null));

        for(char key : alphabet.keySet()){
            int position = getRandomNumber(0,chars.size());
            alphabet.put(key,chars.get(position));
            chars.remove(position);
        }

        return alphabet;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void close() throws Exception {

    }
}
