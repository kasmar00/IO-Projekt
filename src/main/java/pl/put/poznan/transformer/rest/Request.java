package pl.put.poznan.transformer.rest;

/**
 * Request class gives input text
 */
public class Request {
    private final String text;
    private final String[] transformations;

    /**
     * Request class constructor
     *
     * @param text              Text to be transformed
     * @param transformations   List of transformations to be applied
     */
    public Request(String text, String[] transformations) {
        this.text = text;
        this.transformations = transformations;
    }

    /**
     * Gets text
     *
     * @return Given text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets transformations
     *
     * @return List of transformations
     */
    public String[] getTransformations() {
        return transformations;
    }
}
