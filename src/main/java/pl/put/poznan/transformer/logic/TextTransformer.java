package pl.put.poznan.transformer.logic;

public class TextTransformer implements Text{
    protected Text transformedText;

    public TextTransformer(Text transformedText){
        this.transformedText=transformedText;
    }

    @Override
    public String modify()
    {
        return transformedText.modify();
    }
}
