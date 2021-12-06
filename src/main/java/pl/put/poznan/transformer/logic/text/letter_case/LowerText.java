package pl.put.poznan.transformer.logic.text.letter_case;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

public class LowerText extends TextTransformer {
    public LowerText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String transform(){
        transformedText.transform();
        return setLower(transformedText);
    }

    private String setLower(Text transformedText){
        return transformedText.transform().toLowerCase();
    }
}
