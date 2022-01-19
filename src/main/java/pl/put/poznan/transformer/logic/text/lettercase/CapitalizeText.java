package pl.put.poznan.transformer.logic.text.lettercase;

import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;


/**
 * Capitalize text class
 */
public class CapitalizeText extends TextTransformer {
    /**
     * Capitalize text class constructor
     * <p>
     * Refers to inherited variable text from parent's class
     *
     * @param text Text to be transformed
     */
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
     * @throws DomainException Main exception thrown during app running
     */
    @Override
    public String transform() throws DomainException {
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
