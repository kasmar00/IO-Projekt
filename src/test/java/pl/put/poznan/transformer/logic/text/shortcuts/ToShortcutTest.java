package pl.put.poznan.transformer.logic.text.shortcuts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import pl.put.poznan.transformer.logic.text.lettercase.*;
import pl.put.poznan.transformer.logic.text.TextImpl;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;

public class ToShortcutTest {
    Text text;

    @Test
    public void testLowerCase() throws DomainException {
        Text mockText = mock(LowerText.class);
        when(mockText.transform()).thenReturn("na przykład test 1");

        text = new ToShortcut(mockText);
        assertEquals("np. test 1", text.transform());
    }

    @Test
    public void testUpperCase() throws DomainException {
        Text mockText = mock(UpperText.class);
        when(mockText.transform()).thenReturn("TAK ZWANY TEST 2");

        text = new ToShortcut(mockText);
        assertEquals("TZW. TEST 2", text.transform());
    }

    @Test
    public void testCapitalisedText() throws DomainException {
        Text mockText = mock(CapitalizeText.class);
        when(mockText.transform()).thenReturn("Między Innymi Test 3");

        text = new ToShortcut(mockText);
        assertEquals("M.In. Test 3", text.transform());
    }

    @Test
    public void testMixedLetterCase() throws DomainException {
        Text mockText = mock(TextImpl.class);
        when(mockText.transform()).thenReturn("test numer 4 eT cetera");

        text = new ToShortcut(mockText);
        assertEquals("test numer 4 eTc.", text.transform());
    }

    @Test
    public void testMixedLetterCaseMultipleShortcuts() throws DomainException {
        Text mockText = mock(TextImpl.class);
        when(mockText.transform()).thenReturn("na PrzYkłAd tAk zWaNY test 5 Et cetera");

        text = new ToShortcut(mockText);
        assertEquals("nP. tzW. test 5 Etc.", text.transform());
    }

    @Test
    public void testWithPeriod() throws DomainException {
        Text mockText = mock(TextImpl.class);
        when(mockText.transform()).thenReturn("test 6 nie taki jak wyżej.");

        text = new ToShortcut(mockText);
        assertEquals("test 6 nie taki jw.", text.transform());
    }

    @Test
    public void testWithDifferentForms() throws DomainException {
        Text mockText = mock(TextImpl.class);
        when(mockText.transform()).thenReturn("tak zwany test 7, tak zwana próba, tak zwane podejście");

        text = new ToShortcut(mockText);
        assertEquals("tzw. test 7, tzw. próba, tzw. podejście", text.transform());
    }

    @Test
    public void testWithCapitalPolishLetters() throws DomainException {
        Text mockText = mock(TextImpl.class);
        when(mockText.transform()).thenReturn("miĘdzy innymi, na przykŁad test 8");

        text = new ToShortcut(mockText);
        assertEquals("m.in., np. test 8", text.transform());
    }

    @Test
    public void testLatex() throws DomainException {
        Text mockText = mock(LatexifyText.class);
        when(mockText.transform()).thenReturn("tak zwany test \\& test 9");

        text = new ToShortcut(mockText);
        assertEquals("tzw. test \\& test 9", text.transform());
    }

    @Test
    public void testWithoutShortcuts() throws DomainException {
        Text mockText = mock(InverseText.class);
        when(mockText.transform()).thenReturn(".01 tset");

        text = new ToShortcut(mockText);
        assertEquals(".01 tset", text.transform());
    }
}
