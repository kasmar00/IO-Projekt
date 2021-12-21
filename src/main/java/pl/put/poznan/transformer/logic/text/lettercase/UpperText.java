package pl.put.poznan.transformer.logic.text.lettercase;


import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

/**
 * Upper given text
 */
public class UpperText extends TextTransformer {
    public UpperText(Text text){
        super(text);
    }

    public static String name = "upper";

    @Override
    public String transform() throws DomainException {
        return setUpper(text.transform());
    }

    /**
     * Upper given text
     *
     * @param text holds the text
     * @return uppered text
     */
    private String setUpper(String text){
        return text.toUpperCase();
    }
}
