package pl.bart;

import pl.bart.algorithm.RandomPermutation;
import pl.bart.exception.InvalidInputException;

public class Main {

    public static void main(String[] args) {
        RandomPermutation randomPermutation = new RandomPermutation();
        try {
            System.out.println(randomPermutation.cipher("asd 343"));
            System.out.println(randomPermutation.getCharacterMap());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
