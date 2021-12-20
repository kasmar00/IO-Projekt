package pl.put.poznan.transformer.logic.text.numbers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final String regex = "[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)";

    public NumberExpandText(Text text) {
        super(text);
    }

    public static String name = "expand";

    @Override
    public String transform() {
        return setNumberExpand(text.transform());
    }

    private String setNumberExpand(String text) {
        ArrayList<String> matches = matchPattern(text);

        try {
            ArrayList<String> converted = convertNumberToText(matches);
            return replaceNumbers(text, converted);
        } catch (IOException | ParseException e) {
            logger.warn("Can't expand number:", e);
            return text;
        }
    }

    /**
     * Matches pattern in given text
     *
     * @param text given text
     * @return all pattern matches in list
     */
    public static ArrayList<String> matchPattern(String text) {
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(text);
        ArrayList<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * Converts numbers to text
     *
     * @param matches is a given list with numbers as strings
     * @return list of numbers converted to text
     * @throws IOException    on problems with opening a file
     * @throws ParseException on problems parsing a JSON
     */
    public static ArrayList<String> convertNumberToText(ArrayList<String> matches) throws IOException, ParseException {
        ArrayList<String> converted = new ArrayList<>();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(
                new FileReader("src/main/resources/NumberToText.json"));

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
                        numbers = (ArrayList<String>) jsonObject.get("ones");
                        result.append(numbers.get(number)).append(" ");
                    } else if (lenbeforedot == 1) {                           // 0
                        result.append("zero ");
                        break;
                    }
                }
                if (i == 2) {                                           // 10-99
                    if (number == 1) {                                      // 10-19
                        numbers = (ArrayList<String>) jsonObject.get("teens");
                        number = match.charAt(place + 1) - '0';
                        result.append(numbers.get(number)).append(" ");
                        break;
                    } else if (number != 0) {                                 // 20-99
                        numbers = (ArrayList<String>) jsonObject.get("tens");
                        result.append(numbers.get(number)).append(" ");
                    }
                }
                if (i == 3) {                                           // 100-999
                    if (number != 0) {
                        numbers = (ArrayList<String>) jsonObject.get("hundreds");
                        result.append(numbers.get(number)).append(" ");
                    }
                }
                if (i == 4) {                                           // 1000-1999
                    if (number == 1) {
                        result.append("tysiąc ");
                    }
                }
            }
            converted.add(result.toString().trim());
        }
        return converted;
    }

    /**
     * Replace numbers to strings
     *
     * @param text      given text
     * @param converted list of numbers converted to text
     * @return text with converted numbers
     */
    public static String replaceNumbers(String text, ArrayList<String> converted) {
        String replaced = text.replaceAll(regex, "\0");
        for (int i = 0, j = 0; i < replaced.length(); ++i) {
            if (replaced.charAt(i) == '\0') {
                replaced = replaced.substring(0, i) + converted.get(j++) + replaced.substring(i + 1);
            }
        }
        return replaced;
    }
}
