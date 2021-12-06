package pl.put.poznan.transformer.logic.text;

import pl.put.poznan.transformer.logic.text.letter_case.CapitalizeText;
import pl.put.poznan.transformer.logic.text.letter_case.LowerText;
import pl.put.poznan.transformer.logic.text.letter_case.UpperText;

public class TextDemo {
    public static void main()
    {
        Text text = new TextImpl("To jest próba");

        Text uptext = new UpperText(new TextImpl("To jest próba"));

        Text lowtext = new LowerText(new TextImpl("To jest próba"));

        Text captext = new CapitalizeText(new TextImpl("To jest próba"));

        System.out.println(text.transform());
        System.out.println(uptext.transform());
        System.out.println(lowtext.transform());
        System.out.println(captext.transform());
    }
}
