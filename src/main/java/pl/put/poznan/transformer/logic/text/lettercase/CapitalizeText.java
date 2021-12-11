package pl.put.poznan.transformer.logic.text.lettercase;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;


/**
 * Copitalize text
 */
public class CapitalizeText extends TextTransformer {
    public CapitalizeText(Text text){
        super(text);
    }

    @Override
    public String transform(){
        return setCapitalize(text.transform());
    }

    /**
     * Capitalize given text
     *
     * @param text holds the text
     * @return capitalized text
     */
    private String setCapitalize(String text){
        String words[] = text.split("\\s");
        String capitalizeText="";
        for (String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeText+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeText.trim();
    }
}