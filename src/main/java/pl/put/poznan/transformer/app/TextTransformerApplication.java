package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
