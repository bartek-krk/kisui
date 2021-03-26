package pl.bart.algorithm;

import pl.bart.common.AsciiConstants;
import pl.bart.exception.InvalidInputException;
import pl.bart.validator.InputValidator;

import java.util.HashMap;
import java.util.Map;

public class RotN implements Encoder {

    private final int offset;
    private final Map<Character,Character> characterMap;

    public RotN(int offset) {
        this.offset = offset;
        this.characterMap = createAlphabet();
    }

    @Override
    public String cipher(String input) throws InvalidInputException {
        StringBuilder stringBuilder = new StringBuilder();
        if (InputValidator.validate(input)) for(char c : input.toCharArray()) stringBuilder.append(characterMap.get(c));
        return stringBuilder.toString();
    }

    public Map<Character,Character> getCharacterMap() {
        return new HashMap<>(this.characterMap);
    }

    private Map<Character,Character> createAlphabet(){
        Map<Character,Character> alphabet = new HashMap<>();
        for (int i=AsciiConstants.LOWERCASE_START; i<=AsciiConstants.LOWERCASE_STOP; i++){
            int increment = this.offset % AsciiConstants.ALPHABET_LENGTH;
            int resultCode = i;
            while (increment-- != 0){
                if (resultCode == AsciiConstants.LOWERCASE_STOP) resultCode = AsciiConstants.LOWERCASE_START;
                else resultCode++;
            }
            alphabet.put((char)i, (char)resultCode);
        }

        for (int i=AsciiConstants.UPPERCASE_START; i<=AsciiConstants.UPPERCASE_STOP; i++){
            int increment = this.offset % AsciiConstants.ALPHABET_LENGTH;
            int resultCode = i;
            while (increment-- != 0){
                if (resultCode == AsciiConstants.UPPERCASE_STOP) resultCode = AsciiConstants.UPPERCASE_START;
                else resultCode++;
            }
            alphabet.put((char)i, (char)resultCode);
        }

        for (int i=AsciiConstants.DIGIT_START; i<=AsciiConstants.DIGIT_STOP; i++){
            int increment = this.offset % AsciiConstants.DIGIT_LENGTH;
            int resultCode = i;
            while (increment-- != 0){
                if (resultCode == AsciiConstants.DIGIT_STOP) resultCode = AsciiConstants.DIGIT_START;
                else resultCode++;
            }
            alphabet.put((char)i, (char)resultCode);
        }

        alphabet.put((char)AsciiConstants.WHITESPACE,(char)AsciiConstants.WHITESPACE);

        return alphabet;
    }

    @Override
    public void close() throws Exception {

    }
}
