package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextImpl;
import pl.put.poznan.transformer.logic.text.lettercase.CapitalizeText;
import pl.put.poznan.transformer.logic.text.lettercase.InverseText;
import pl.put.poznan.transformer.logic.text.lettercase.UpperText;
import pl.put.poznan.transformer.logic.text.numbers.NumberExpandText;
import pl.put.poznan.transformer.logic.text.shortcuts.FromShortcut;

/**
 * Text transformer application class
 * <p>
 * Root of the text transformer application
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer"})
public class TextTransformerApplication {

    /**
     * Runs text transformer application
     * <p>
     * Runs text transformer application using Spring Boot
     *
     * @param args List of arguments to be used by spring application
     */
    public static void main(String[] args) throws DomainException {
        Text test = new InverseText (new CapitalizeText (new FromShortcut(new  NumberExpandText(new UpperText(new TextImpl("Cześć, to testowy tekst nr 654.43 i ciekawe czy wyjdzie np. ;)"))))));
        System.out.println(test.transform());

        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
