package pl.put.poznan.transformer.logic.text.letter_case;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

/**
 * Lower given text
 */
public class LowerText extends TextTransformer {
    public LowerText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String transform(){
        transformedText.transform();
        return setLower(transformedText);
    }
    /**
     * Lower given text
     *
     * @param transformedText holds the text
     * @return lowered text
     */
    private String setLower(Text transformedText){
        return transformedText.transform().toLowerCase();
    }
}
