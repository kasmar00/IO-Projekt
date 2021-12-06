package pl.put.poznan.transformer.logic.text.letter_case;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;


/**
 * Copitalize text
 */
public class CapitalizeText extends TextTransformer {
    public CapitalizeText(Text transformedText){
        super(transformedText);
    }

    @Override
    public String transform(){
        transformedText.transform();
        return setCapitalize(transformedText);
    }

    /**
     * Capitalize given text
     *
     * @param transformedText holds the text
     * @return capitalized text
     */
    private String setCapitalize(Text transformedText){
        String words[] = transformedText.transform().split("\\s");
        String capitalizeText="";
        for (String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeText+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeText.trim();
    }
}