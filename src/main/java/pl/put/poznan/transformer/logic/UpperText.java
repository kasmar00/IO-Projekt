package pl.put.poznan.transformer.logic;


public class UpperText extends TextTransformer{
    public UpperText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String modify(){
        transformedText.modify();
        return setUpper(transformedText);
    }

    private String setUpper(Text transformedText){
        return transformedText.modify().toUpperCase();
    }
}
