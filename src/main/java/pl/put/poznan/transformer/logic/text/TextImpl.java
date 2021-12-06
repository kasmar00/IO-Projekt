package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.text.Text;

public class TextImpl implements Text {
    String pom;
    TextImpl(String words)
    {
        pom = words;
    }
    @Override
    public String transform() {
        return pom;
    }
}
