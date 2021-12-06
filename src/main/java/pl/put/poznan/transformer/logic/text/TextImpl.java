package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.text.Text;

/**
 * Implements text to class
 */
public class TextImpl implements Text {
    String pom;
    TextImpl(String words)
    {
        pom = words;
    }

    /**
     * Return given text
     * @return text
     */
    @Override
    public String transform() {
        return pom;
    }
}
