package mycinema.exceptions;

public class RegistrationException extends Exception {
    public RegistrationException() {
        super("Login is already exist");
    }
}
