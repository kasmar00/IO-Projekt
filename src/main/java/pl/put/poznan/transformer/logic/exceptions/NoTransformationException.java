package pl.put.poznan.transformer.logic.exceptions;

public class NoTransformationException extends DomainException {
    public NoTransformationException(String transformation) {
        super("No such transformation: " + transformation);
    }
}
