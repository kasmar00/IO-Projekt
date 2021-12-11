package pl.put.poznan.transformer.logic.text.lettercase;


import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

/**
 * Upper given text
 */
public class UpperText extends TextTransformer {
    public UpperText(Text text){
        super(text);
    }

    @Override
    public String transform(){
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
