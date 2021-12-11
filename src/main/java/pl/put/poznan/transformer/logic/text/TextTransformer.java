package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.text.Text;

public class TextTransformer implements Text {
    protected Text text;

    public TextTransformer(Text transformedText){
        this.text=transformedText;
    }

    @Override
    public String transform()
    {
        return text.transform();
    }
}
