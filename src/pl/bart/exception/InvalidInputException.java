package pl.bart.exception;

public class InvalidInputException extends Exception{

    private Reason reason;

    public InvalidInputException(Reason reason) {
        super();
        this.reason = reason;
    }

    public enum Reason {
        EMPTY_STRING("Wiadomość nie może być pusta!"),
        INVALID_CHARACTERS("Dozwolone są tylko znaki alfabetu angielskiego i liczby!");

        public String message;

        private Reason(String message) {
            this.message = message;
        }
    }

    public Reason getReason() {
        return reason;
    }

}
