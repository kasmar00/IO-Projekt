package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.text.Text;

public class TextTransformer implements Text {
    protected Text transformedText;

    public TextTransformer(Text transformedText){
        this.transformedText=transformedText;
    }

    @Override
    public String transform()
    {
        return transformedText.transform();
    }
}
