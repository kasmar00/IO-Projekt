package pl.put.poznan.transformer.logic.text.shortcuts;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextImpl;
import pl.put.poznan.transformer.logic.text.TextTransformer;
import pl.put.poznan.transformer.logic.text.lettercase.CapitalizeText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FromShortcut extends TextTransformer{
    public static String name = "from shortcuts";

    private final String[][] pairs = {
            {"na przykład", "np."},
            {"i tym podobne", "itp."},
            {"i tak dalej", "itd."},
            {"et cetera", "etc."},
            {"to znaczy", "tzn."},
            {"tak zwane", "tzw."},
            {"między innymi", "m.in."},
            {"na temat", "nt."},
            {"jak wyżej", "jw."},
            {"profesor", "prof."},
            {"doktor", "dr"}
    };


    public FromShortcut(Text text) {
        super(text);
    }

    @Override
    public String transform() {
        return expand(text.transform());
    }

    private String[] capitalizeExpansion(String[] splitedText, String match){
        int counter=0;
        for(int j = 0; j < match.length() - 1; j++){
            if(match.charAt(j) == '.') continue;
            else{
                if(Character.isUpperCase(match.codePointAt(j)) && counter<splitedText.length){
                    Text capitalize = new CapitalizeText(new TextImpl(splitedText[counter]));
                    splitedText[counter]=capitalize.transform();
                }
            }
            counter+=1;
        }
        return splitedText;
    }

    /**
     * Turns words from shortcuts
     *
     * @param text holds the text
     * @return expanded text
     */


    private String expand(String text) {
        String expandedText = null;
        String workingText = text;
        Pattern pattern;
        Matcher matcher;
        String match, expansion;
        for(int i = 0; i < pairs.length; i++) {
            pattern = Pattern.compile(pairs[i][1], Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(workingText);
            expandedText = "";
            int index = 0;
            expansion = pairs[i][0];
            while(matcher.find()) {
                match = workingText.substring(matcher.start(), matcher.end());
                String[] splitedText=expansion.split(" ");
                String joinedText=String.join(" ", capitalizeExpansion(splitedText, match));
                expandedText += workingText.substring(index, matcher.start()) + joinedText;
                index = matcher.end();
            }
            expandedText += workingText.substring(index);
            workingText = expandedText;
        }
        return expandedText;
    }

}



