package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.exceptions.NumberConvertingException;

public class TextTransformer implements Text {
    protected Text text;

    /**
     * Transformation name
     */
    public static String name = "noop";

    public TextTransformer(Text transformedText){
        this.text=transformedText;
    }

    @Override
    public String transform() throws DomainException {
        return text.transform();
    }
}
