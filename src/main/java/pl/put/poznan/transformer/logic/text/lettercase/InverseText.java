package pl.put.poznan.transformer.logic.text.lettercase;


import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;
import java.lang.Character;
import java.lang.Override;
import java.lang.String;


/**
 * Inverse text
 */
public class InverseText extends TextTransformer {
    public InverseText(Text text){
        super(text);
    }

    @Override
    public String transform(){
        return getInverse(text.transform());
    }

    /**
     * Reverse given text
     *
     * @param text holds the text
     * @return reversed text
     */
    private String getInverse(String text){
        char[] tempArray = text.toCharArray();
        int left;
        int right = tempArray.length - 1;

        for (left = 0; left < right; left++, right--) {
            if(Character.isUpperCase(tempArray[left])){
                char temp = Character.toLowerCase(tempArray[left]);
                tempArray[left] = Character.toUpperCase(tempArray[right]);;
                tempArray[right] = temp;
            }
            else if(Character.isUpperCase(tempArray[right])){
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
        return new String(tempArray);
    }
}