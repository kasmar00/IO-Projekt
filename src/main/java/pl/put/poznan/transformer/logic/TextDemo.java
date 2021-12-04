package pl.put.poznan.transformer.logic;

public class TextDemo {
    public static void main()
    {
        Text text = new TextImpl();

        Text uptext = new UpperText(new TextImpl());

        Text lowtext = new LowerText(new TextImpl());

        Text captext = new CapitalizeText(new TextImpl());

        System.out.println(text.modify());
    }
}
