package pl.put.poznan.transformer.logic.text.lettercase;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

/**
 * Lower given text
 */
public class LowerText extends TextTransformer {
    public LowerText(Text text){
        super(text);
    }

    public static String name = "lower";

    @Override
    public String transform(){
        return setLower(text.transform());
    }
    /**
     * Lower given text
     *
     * @param text holds the text
     * @return lowered text
     */
    private String setLower(String text){
        return text.toLowerCase();
    }
}
