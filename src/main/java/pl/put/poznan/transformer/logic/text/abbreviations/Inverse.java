package pl.put.poznan.transformer.logic.text.abbreviations;


import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;
import java.lang.*;


/**
 * Inverse text
 */
public class Inverse extends TextTransformer {
    public Inverse(Text text){
        super(text);
    }

    @Override
    public String transform(){
        return reverse(text.transform());
    }

    /**
     * Reverse given text
     *
     * @param text holds the text
     * @return reversed text
     */
    private String reverse(String text){
        char[] temparray = text.toCharArray();
        int left = 0;
        int right = temparray.length - 1;

        for (left = 0; left < right; left++, right--) {
            if(Character.isUpperCase(temparray[left])){
                char temp = Character.toLowerCase(temparray[left]);
                temparray[left] = Character.toUpperCase(temparray[right]);;
                temparray[right] = temp;
            }
            else if(Character.isUpperCase(temparray[right])){
                char temp = Character.toUpperCase(temparray[left]);
                temparray[left] = Character.toLowerCase(temparray[right]);
                temparray[right] = temp;
            }
            else {
                char temp = temparray[left];
                temparray[left] = temparray[right];
                temparray[right] = temp;
            }
        }
        text = new String(temparray);
        return text;
    }
}