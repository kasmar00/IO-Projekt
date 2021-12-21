package pl.put.poznan.transformer.logic.text.lettercase;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;


/**
 * Capitalize text class
 * <p>
 * Capitalizes text
 */
public class CapitalizeText extends TextTransformer {
    public CapitalizeText(Text text){
        super(text);
    }

    /**
     * Keyword for capitalize text transformation
     */
    public static String name = "capitalize";

    /**
     * Makes capitalize to text transformation
     *
     * @return Text capitalized
     */
    @Override
    public String transform(){
        return setCapitalize(text.transform());
    }

    /**
     * Capitalize given text
     *
     * @param text Holds the text
     * @return Capitalized text
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
