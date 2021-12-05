package pl.put.poznan.transformer.rest;

public class TransformerRequest {
    public String text;
    public String[] transforms;

    public TransformerRequest(String text, String[] transforms) {
        this.text = text;
        this.transforms = transforms;
    }
}
