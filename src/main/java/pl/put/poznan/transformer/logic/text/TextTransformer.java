package pl.put.poznan.transformer.logic.text;

public class TextTransformer implements Text {
    protected Text text;

    /**
     * Transformation name
     */
    public static String name = "noop";

    public TextTransformer(Text transformedText){
        this.text=transformedText;
    }

    @Override
    public String transform() {
        return text.transform();
    }
}
