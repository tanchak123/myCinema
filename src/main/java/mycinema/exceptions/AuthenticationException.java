package mycinema.exceptions;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super("Incorrect login or password");
    }
}
