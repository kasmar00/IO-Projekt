package pl.put.poznan.transformer.logic.text.lettercase;

import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextTransformer;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Remove duplicate text
 * <p>
 * Removes duplicates from the given text
 */
public class DuplicateRemoveText extends TextTransformer {
    /**
     * Duplicate remove text class constructor
     * <p>
     * Refers to inherited variable text from parent's class
     *
     * @param text Text to be transformed
     */
    public DuplicateRemoveText(Text text) {super(text);}

    /**
     * Keyword for remove duplicates text transformation
     */
    public static String name = "duplicate";

    /**
     * Removes duplicates from the given text
     *
     * @return Text with removed duplicates
     * @throws DomainException Main exception thrown during app running
     */
    @Override
    public String transform() throws DomainException {
        return setRemoveDuplicate(text.transform());
    }

    /**
     * Transforms text removing duplicates from the given text
     *
     * @param text Holds the text
     * @return Text without duplicates
     */
    private String setRemoveDuplicate(String text) {
        String[] words = text.split(" ");

        ArrayList<String> result = new ArrayList<>();
        result.add(words[0]);
        for (int i = 1; i < words.length; ++i) {
            if (!Objects.equals(result.get(result.size() - 1), words[i])) {
                result.add(words[i]);
            }
        }
        return String.join(" ", result);
    }
}
