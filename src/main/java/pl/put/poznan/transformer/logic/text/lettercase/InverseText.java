package pl.put.poznan.transformer.logic.text.lettercase;


import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;
import java.lang.Character;
import java.lang.Override;
import java.lang.String;


/**
 * Inverse text class
 */
public class InverseText extends TextTransformer {
    /**
     * Inverse text class constructor
     * <p>
     * Refers to inherited variable text from parent's class
     *
     * @param text Text to be transformed
     */
    public InverseText(Text text){
        super(text);
    }

    /**
     * Keyword for inverse text transformation
     */
    public static String name = "inverse";

    /**
     * Makes inverse to text transformation
     *
     * @return Text inversed
     * @throws DomainException Main exception thrown during app running
     */
    @Override
    public String transform() throws DomainException {
        return getInverse(text.transform());
    }

    /**
     * Reverse given text
     *
     * @param text Holds the text
     * @return Reversed text
     */
    private String getInverse(String text){
        char[] tempArray = text.toCharArray();
        int left;
        int right = tempArray.length - 1;

        for (left = 0; left < right; left++, right--) {
            if(Character.isUpperCase(tempArray[left])){
                if(Character.isUpperCase(tempArray[right])){
                    char temp = tempArray[left];
                    tempArray[left] = tempArray[right];
                    tempArray[right] = temp;
                }
                else {
                    char temp = Character.toLowerCase(tempArray[left]);
                    tempArray[left] = Character.toUpperCase(tempArray[right]);
                    tempArray[right] = temp;
                }
            }
            else {
                if(Character.isUpperCase(tempArray[right])){
                    char temp = Character.toUpperCase(tempArray[left]);
                    tempArray[left] = Character.toLowerCase(tempArray[right]);
                    tempArray[right] = temp;
                }
                else {
                    char temp = tempArray[left];
                    tempArray[left] = tempArray[right];
                    tempArray[right] = temp;
                }
            }
        }
        return new String(tempArray);
    }
}