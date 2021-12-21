package pl.put.poznan.transformer.logic.text.shortcuts;

import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Turns words into shortcuts
 */
public class ToShortcut extends TextTransformer {

    public static String name = "to shortcuts";

    private final String[][] pairs = {
            {"na przykład", "np.", "03"},
            {"i tym podobne", "itp.", "026"},
            {"i tak dalej", "itd.", "026"},
            {"et cetera", "etc.", "013"},
            {"to znaczy", "tzn.", "034"},
            {"tak zwan.", "tzw.", "045"},
            {"między innymi", "m.in.", "0.78"},
            {"na temat", "nt.", "03"},
            {"jak wyżej", "jw.", "04"}
    };


    public ToShortcut(Text text) {
        super(text);
    }

    @Override
    public String transform() throws DomainException {
        return shorten(text.transform());
    }

    /**
     * Turns words into shortcuts
     *
     * @param text holds the text
     * @return text with shortcuts
     */
    private String shorten(String text) {
        String shortenedText = null;
        String workingText = text;
        Pattern pattern;
        Matcher matcher;
        String match, shortcut;
        for(int i = 0; i < pairs.length; i++) {
            pattern = Pattern.compile(pairs[i][0], Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(workingText);
            shortenedText = "";

            int index = 0;
            while(matcher.find()) {
                match = workingText.substring(matcher.start(), matcher.end());
                shortcut = pairs[i][1];
                for(int j = 0; j < shortcut.length() - 1; j++){
                    if(shortcut.charAt(j) == '.') continue;
                    else{
                        int position = Character.getNumericValue(pairs[i][2].charAt(j));
                        if(match.charAt(position) < 97){
                            shortcut = shortcut.substring(0, j) + shortcut.substring(j, j + 1).toUpperCase() + shortcut.substring(j + 1);
                        }
                    }
                }
                shortenedText += workingText.substring(index, matcher.start()) + shortcut;
                index = matcher.end();

                // usuwanie podwójnych kropek
                if(pairs[i][1].charAt(pairs[i][1].length() - 1) == '.' &&
                        workingText.charAt(matcher.end()) == '.') {
                    index += 1;
                };
            }
            shortenedText += workingText.substring(index);
            workingText = shortenedText;
        }
        return shortenedText;
    }
}
