package pl.put.poznan.transformer.logic.text.shortcuts;

import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Turns words into shortcuts
 */
public class ToShortcut extends TextTransformer {

    private final String[][] pairs = {
            {"na przykład", "np."},
            {"i tym podobne", "itp."},
            {"i tak dalej", "itd."},
            {"et cetera", "etc."},
            {"to znaczy", "tzn."},
            {"tak zwan.", "tzw."},
            {"to jest", "tj."},
            {"między innymi", "m.in."},
            {"na temat", "nt."},
            {"jak wyżej", "jw."},
            {"doktor habilitowany", "dr hab."},
            {"doktor", "dr"},
            {"profesor", "prof."},
            {"magister inżynier", "mgr inż."},
            {"magister", "mgr"},
            {"inżynier", "inż."}
    };


    public ToShortcut(Text text) {
        super(text);
    }

    @Override
    public String transform() {
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
        for(int i = 0; i < pairs.length; i++) {
            pattern = Pattern.compile(pairs[i][0]);
            matcher = pattern.matcher(workingText);
            shortenedText = "";

            int index = 0;
            while(matcher.find()) {
                shortenedText += workingText.substring(index, matcher.start()) + pairs[i][1];
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
