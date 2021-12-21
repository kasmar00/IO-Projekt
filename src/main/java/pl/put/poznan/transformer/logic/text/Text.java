package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.exceptions.DomainException;

/**
 * Text interface which handles transform method
 */
public interface Text {

    /**
     * Applying transformation to object
     *
     * @return string with applied transformation
     */
    String transform() throws DomainException;
}
