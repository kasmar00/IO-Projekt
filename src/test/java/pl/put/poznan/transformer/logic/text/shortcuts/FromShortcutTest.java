package pl.put.poznan.transformer.logic.text.shortcuts;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextImpl;
import pl.put.poznan.transformer.logic.text.shortcuts.FromShortcut;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FromShortcutTest {
    Text text;

    @Test
    public void testAllIsLowerCase() throws DomainException {
        text = new FromShortcut(new TextImpl("np."));
        assertEquals("na przykład", text.transform());

    }

    @Test
    public void testFirstIsUpperCase() throws DomainException {
        text = new FromShortcut(new TextImpl("Np."));
        assertEquals("Na przykład", text.transform());

    }

    @Test
    public void testLastIsLowerCase() throws DomainException {
        text = new FromShortcut(new TextImpl("m.in."));
        assertEquals("między innymi", text.transform());

    }

    @Test
    public void testMiddleIsLowerCase() throws DomainException {
        text = new FromShortcut(new TextImpl("m.In."));
        assertEquals("między Innymi", text.transform());

    }

    @Test
    public void testAllIsUpperCase() throws DomainException {
        text = new FromShortcut(new TextImpl("M.IN."));
        assertEquals("Między Innymi", text.transform());

    }

    @Test
    public void testExtraDot() throws DomainException {
        text = new FromShortcut(new TextImpl("m.in.."));
        assertEquals("między innymi.", text.transform());
    }

    @Test
    public void testSeveralShortcuts() throws DomainException {
        text = new FromShortcut(new TextImpl("m.in. np."));
        assertEquals("między innymi na przykład", text.transform());
    }

    @Test
    public void testEmptyText() throws DomainException {
        text = new FromShortcut(new TextImpl(""));
        assertEquals("", text.transform());
    }

    @Test
    public void testTextWithoutShortcuts() throws DomainException {
        text = new FromShortcut(new TextImpl("Życie bracie"));
        assertEquals("Życie bracie", text.transform());
    }

    @Test
    public void testNormalTextWithShortcuts() throws DomainException {
        text = new FromShortcut(new TextImpl("Życie bracie, m.in. błędne przeświadczenie o np. Mickiewiczu"));
        assertEquals("Życie bracie, między innymi błędne przeświadczenie o na przykład Mickiewiczu", text.transform());
    }
}