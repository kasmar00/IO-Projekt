package pl.put.poznan.transformer.logic.text;

/**
 * Implements text to class
 */
public class TextImpl implements Text {
    String text;
    TextImpl(String words)
    {
        text = words;
    }

    /**
     * Return given text
     * @return outcome
     */
    @Override
    public String transform() {
        return text;
    }
}
