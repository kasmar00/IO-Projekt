package pl.put.poznan.transformer.logic.text.numbers;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Expands numbers to text class
 * <p>
 * Expands every number to text
 */
public class NumberExpandText extends TextTransformer {

    private static final Logger logger = LoggerFactory.getLogger(NumberExpandText.class);
    private static final String regex = "[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)";

    /**
     * Number expand text class constructor
     * <p>
     * Refers to inherited variable text from parent's class
     *
     * @param text Text to be transformed
     */
    public NumberExpandText(Text text) {
        super(text);
    }

    /**
     * Keyword for expand numbers to text transformation
     */
    public static String name = "expand";

    /**
     * Makes expand number to text transformation
     *
     * @return Text with expanded numbers to text
     */
    @Override
    public String transform() {
        return setNumberExpand(text.transform());
    }

    /**
     * Sets expand number to text transformation
     * <p>
     * Matches numbers in given text, converts numbers to text and replaces them in text
     *
     * @param text Text to be transformed
     * @return Text with expanded numbers to text
     */
    private String setNumberExpand(String text) {
        ArrayList<String> matches = matchPattern(text);

        try {
            ArrayList<String> converted = this.convertNumberToText(matches);
            return replaceNumbers(text, converted);
        } catch (IOException | ParseException e) {
            logger.warn("Can't expand number:", e);
            return text;
        }
    }

    /**
     * Matches pattern in given text
     *
     * @param text  Text to be searched with pattern
     * @return      All pattern matches in list
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

    private Map<String, List<String>> getNameDictionary() throws ParseException {
        InputStream inputStream = this.getClass().getResourceAsStream("/NumberToText.json");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Stream<String> lines = bufferedReader.lines();

        return (Map<String, List<String>>) new JSONParser().parse(
                lines.collect(Collectors.joining(""))
        );
    }

    /**
     * Converts every searched number to text
     * <p>
     * Calculates number of digits and replaces digits with text using JSON file
     *
     * @param matches Given list with numbers as strings
     * @return List of numbers converted to text
     * @throws IOException    On problems with opening a file
     * @throws ParseException On problems parsing a JSON
     */
    public ArrayList<String> convertNumberToText(ArrayList<String> matches) throws IOException, ParseException {
        ArrayList<String> converted = new ArrayList<>();
        Map<String, List<String>> jsonObject = getNameDictionary();

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
                    } else if (lenbeforedot == 1) {                         // 0
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
                    } else if (number != 0) {                               // 20-99
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
            String convertedNumber = result.toString().trim();
            if (lenafterdot > 0) {
                String decimalPart = convertDecimalPart(match, jsonObject);
                convertedNumber += " i " + decimalPart.trim();
            }
            converted.add(convertedNumber);
        }
        return converted;
    }


    private static int extensionSelector(int value) {
        if (value == 1) {
            return 0;
        } else if (value <= 4) {
            return 1;
        } else if (value <= 20) {
            return 2;
        } else if (Arrays.asList(2, 3, 4).contains(value % 10)) {
            return 1;
        } else {
            return 2;
        }
    }

    private static String convertDecimalPart(String decimalPartInput, Map<String, List<String>> dictionary) {
        int splitIndex = decimalPartInput.indexOf(".");
        int decimalPart = (int) (Double.parseDouble(decimalPartInput.substring(splitIndex)) * 100);

        if (decimalPart == 0) {
            return "";
        } else if (decimalPart % 10 == 0) {
            int value = decimalPart / 10;
            String name = dictionary.get("places").get(value);
            String extension = dictionary.get("decimal").get(extensionSelector(value));
            return name + " " + extension;
        } else {
            String name = getHundredthPart(decimalPart, dictionary);
            String extension = dictionary.get("hundredth").get(extensionSelector(decimalPart));
            return name + " " + extension;
        }
    }

    private static String getHundredthPart(int value, Map<String, List<String>> dictionary) {
//        todo: generalize this function
        List<String> expandedText = new ArrayList<>();

        {
            int i = value / 10;
            if (i >= 2) {
                expandedText.add(dictionary.get("tens").get(i));
                value -= i * 10;
            }
        }

        {
            int i = value - 10;
            if (i >= 2) {
                expandedText.add(dictionary.get("teens").get(i));
                value -= i + 10;
            }
        }

        {
            int i = value;
            if (i >= 1) {
                expandedText.add(dictionary.get("places").get(i));
                value -= i;
            }
        }

        return String.join(" ", expandedText);
    }

    /**
     * Replaces numbers to strings in text
     *
     * @param text      Text to be transformed
     * @param converted List of numbers converted to text
     * @return Text with converted numbers
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
