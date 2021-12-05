package pl.put.poznan.transformer.rest;

public class Request {
    private final String text;
    private final String[] transofrmations;

    public Request(String text, String[] transofrmations) {
        this.text = text;
        this.transofrmations = transofrmations;
    }

    public String getText() {
        return text;
    }

    public String[] getTransofrmations() {
        return transofrmations;
    }
}
