package pl.put.poznan.transformer.logic;

public class LowerText extends TextTransformer{
    public LowerText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String modify(){
        transformedText.modify();
        return setLower(transformedText);
    }

    private String setLower(Text transformedText){
        return transformedText.modify().toLowerCase();
    }
}
