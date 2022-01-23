package pl.put.poznan.transformer.logic.text.lettercase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;

public class LowerTextTest {
    Text text;

    @Test
    public void testAllIsUpperCase() throws DomainException {
        Text mockText = mock(LatexifyText.class);
        when(mockText.transform()).thenReturn("PRZYKŁADOWY TEKST \\$");

        text = new LowerText(mockText);

        assertEquals("przykładowy tekst \\$", text.transform());
    }
}
