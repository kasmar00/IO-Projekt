package pl.put.poznan.transformer.logic.text.lettercase;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextImpl;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTextTest {
    Text text;
    @Test
    public void testAllIsLowerCase() throws DomainException {
        text = new CapitalizeText(new TextImpl("przykładowy test"));
        assertEquals("Przykładowy Test", text.transform());

    }

    @Test
    public void testFirstIsLowerCase() throws DomainException {
        text = new CapitalizeText(new TextImpl("pRZYKŁADOWY TEST"));
        assertEquals("PRZYKŁADOWY TEST", text.transform());

    }

    @Test
    public void testLastIsLowerCase() throws DomainException {
        text = new CapitalizeText(new TextImpl("PRZYKŁADOWY tEST"));
        assertEquals("PRZYKŁADOWY TEST", text.transform());

    }

    @Test
    public void testMixedLetterCases() throws DomainException {
        text = new CapitalizeText(new TextImpl("pRzykŁadowy TeSt"));
        assertEquals("PRzykŁadowy TeSt", text.transform());

    }

    @Test
    public void testAllIsUpperCase() throws DomainException {
        text = new CapitalizeText(new TextImpl("PRZYKŁADOWY TEST"));
        assertEquals("PRZYKŁADOWY TEST", text.transform());

    }
}