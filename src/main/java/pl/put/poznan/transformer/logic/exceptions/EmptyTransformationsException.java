package pl.put.poznan.transformer.logic.exceptions;

public class EmptyTransformationsException extends DomainException {
    public EmptyTransformationsException() {
        super("Empty list of transformations");
    }
}
