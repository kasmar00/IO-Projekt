package pl.put.poznan.transformer.logic.text.lettercase;


import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

/**
 * Upper given text
 */
public class UpperText extends TextTransformer {
    public UpperText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String transform(){
        text.transform();
        return setUpper(text);
    }

    /**
     * Upper given text
     *
     * @param text holds the text
     * @return uppered text
     */
    private String setUpper(Text text){
        return text.transform().toUpperCase();
    }
}
