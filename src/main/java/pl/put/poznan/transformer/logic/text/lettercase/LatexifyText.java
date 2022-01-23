package pl.put.poznan.transformer.logic.text.lettercase;

import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

/**
 * Latexify text class
 * <p>
 * Turns text into text with latex syntax
 */
public class LatexifyText extends TextTransformer {
    /**
     * Latexify text class constructor
     * <p>
     * Refers to inherited variable text from parent's class
     *
     * @param text Text to be transformed
     */
    public LatexifyText(Text text) {super(text);}

    /**
     * Keyword for latex syntax text transformation
     */
    public static String name = "latexify";

    /**
     * Makes latex syntax to text transformation
     *
     * @return Text with latex syntax
     * @throws DomainException Main exception thrown during app running
     */
    @Override
    public String transform() throws DomainException {
        return setLatexify(text.transform());
    }

    /**
     * Transforms text into text with latex syntax
     *
     * @param text Holds the text
     * @return Text with latex syntax
     */
    private String setLatexify(String text) {
        String[] signs = {"\\{", "}", "\\$", "_", "%", "&", "#"};

        for (String sign : signs) {
            text = text.replaceAll(sign, "\\\\" + sign);
        }
        return text;
    }
}
