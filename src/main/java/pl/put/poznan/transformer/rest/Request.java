package pl.put.poznan.transformer.rest;

public class Request {
    private final String text;
    private final String[] transformations;

    public Request(String text, String[] transformations) {
        this.text = text;
        this.transformations = transformations;
    }

    public String getText() {
        return text;
    }

    public String[] getTransformations() {
        return transformations;
    }
}
