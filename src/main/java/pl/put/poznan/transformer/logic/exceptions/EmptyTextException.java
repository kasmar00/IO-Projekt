package pl.put.poznan.transformer.logic.exceptions;

public class EmptyTextException extends DomainException {
    public EmptyTextException() {
        super("No text provided");
    }
}
