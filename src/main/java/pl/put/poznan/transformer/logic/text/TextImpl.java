package pl.put.poznan.transformer.logic.text;

/**
 * Implements text to class
 */
public class TextImpl implements Text {
    String outcome;
    TextImpl(String words)
    {
        outcome = words;
    }

    /**
     * Return given text
     * @return text
     */
    @Override
    public String transform() {
        return outcome;
    }
}
