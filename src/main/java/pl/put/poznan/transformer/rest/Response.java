package pl.put.poznan.transformer.rest;

/**
 * Response class gives output text
 */
public class Response {
    private final String text;

    /**
     * Response class constructor
     *
     * @param text Text after transformations
     */
    public Response(String text) {
        this.text = text;
    }

    /**
     * Gets text
     *
     * @return Transformed text
     */
    public String getText() {
        return text;
    }
}
