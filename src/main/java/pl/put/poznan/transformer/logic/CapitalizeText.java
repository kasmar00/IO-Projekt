package pl.put.poznan.transformer.logic;

public class CapitalizeText extends TextTransformer{
    public CapitalizeText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String modify(){
        transformedText.modify();
        return setCapitalize(transformedText);
    }

    private String setCapitalize(Text transformedText){
        String words[] = transformedText.modify().split("\\s");
        String capitalizeText="";
        for (String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeText+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeText.trim();
    }
}