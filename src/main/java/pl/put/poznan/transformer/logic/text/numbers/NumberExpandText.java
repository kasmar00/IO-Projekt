package pl.put.poznan.transformer.logic.text.numbers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.TextTransformerService;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Expand numbers to text
 */
public class NumberExpandText extends TextTransformer {
    private static final Logger logger = LoggerFactory.getLogger(NumberExpandText.class);

    public NumberExpandText(Text text){
        super(text);
    }

    public static String name = "expand";

    @Override
    public String transform() {
        return setNumberExpand(text.transform());
    }

    private String setNumberExpand(String text) {
        ArrayList<String> matches = MatchPattern(text);

        try {
            ArrayList<String> converted = ConvertNumberToText(matches);
            return ReplaceNumbers(text, converted);
        } catch (IOException | ParseException e) {
            logger.warn("Can't expand number:", e);
            return text;
        }
    }

    /**
     * Matches pattern in given text
     * @return all pattern matches in list
     */
    public static ArrayList<String> MatchPattern(String text) {
        String regex = "[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(text);
        ArrayList<String> matches = new ArrayList<>();
        while(matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * Converts numbers to text
     * @param matches is a given list with numbers as strings
     * @throws IOException
     * @throws ParseException
     * @return list of numbers converted to text
     */
    public static ArrayList<String> ConvertNumberToText(ArrayList<String> matches) throws IOException, ParseException {
        ArrayList<String> converted = new ArrayList<>();
        Object object = new JSONParser().parse(new FileReader("src/main/resources/NumberToText.json"));
        JSONObject jo = (JSONObject) object;

        int lenbeforedot, lenafterdot;
        for (String match : matches) {
            lenbeforedot = lenafterdot = 0;
            if (match.endsWith(".")) {
                match = match.substring(0, match.length() - 1);
                lenbeforedot = match.length();
            } else if (match.contains(".")) {
                String[] parts = match.split("\\.");
                lenbeforedot = parts[0].length();
                lenafterdot = parts[1].length();
            } else {
                lenbeforedot = match.length();
            }

            int number;
            ArrayList<String> numbers;
            StringBuilder result = new StringBuilder();
            for (int i = lenbeforedot, place = 0; i > 0; --i, ++place) {
                number = match.charAt(place) - '0';
                if (i == 1) {                                           // 0-9
                    if (number != 0) {                                      // 1-9
                        numbers = (ArrayList<String>) jo.get("ones");
                        result.append(numbers.get(number)).append(" ");
                    }
                    else if (lenbeforedot == 1) {                           // 0
                        result.append("zero ");
                        break;
                    }
                }
                if (i == 2) {                                           // 10-99
                    if (number == 1) {                                      // 10-19
                        numbers = (ArrayList<String>) jo.get("teens");
                        number = match.charAt(place + 1) - '0';
                        result.append(numbers.get(number)).append(" ");
                        break;
                    }
                    else if (number != 0) {                                 // 20-99
                        numbers = (ArrayList<String>) jo.get("tens");
                        result.append(numbers.get(number)).append(" ");
                    }
                }
                if (i == 3) {                                           // 100-999
                    if (number != 0) {
                        numbers = (ArrayList<String>) jo.get("hundreds");
                        result.append(numbers.get(number)).append(" ");
                    }
                }
                if (i == 4) {                                           // 1000-19999
                    if (number == 1) {
                        result.append("tysiąc ");
                    }
                }
            }
            converted.add(result.toString());
        }
        return converted;
    }

    /**
     * Replace numbers to strings
     * @param text given text
     * @param converted list of numbers converted to text
     * @return text with converted numbers
     */
    public static String ReplaceNumbers(String text, ArrayList<String> converted) {
        String[] splited = text.split("[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)");

        for (int i = 0; i < splited.length; ++i) {
            splited[i] = splited[i].trim();
            splited[i] = splited[i] + " " + converted.get(i);
        }

        return String.join("", splited).trim();
    }
}
