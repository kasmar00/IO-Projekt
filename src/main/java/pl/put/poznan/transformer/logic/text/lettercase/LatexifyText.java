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
    public LatexifyText(Text text) {super(text);}

    /**
     * Keyword for latex syntax text transformation
     */
    public static String name = "latexify";

    /**
     * Makes latex syntax to text transformation
     *
     * @return Text with latex syntax
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
