package pl.put.poznan.transformer.logic.text.letter_case;


import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

public class UpperText extends TextTransformer {
    public UpperText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String transform(){
        transformedText.transform();
        return setUpper(transformedText);
    }

    private String setUpper(Text transformedText){
        return transformedText.transform().toUpperCase();
    }
}
