package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerService;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.exceptions.EmptyTextException;
import pl.put.poznan.transformer.logic.exceptions.EmptyTransformationsException;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Text transformer controller class
 * <p>
 * Rest controller class to text transformations
 */
@RestController
@RequestMapping("/api/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private final TextTransformerService textTransformerService;

    /**
     * Text transformer controller constructor
     * <p>
     * A text transformer service is constructed
     *
     * @param textTransformerService Service class instance to transform text
     */
    public TextTransformerController(TextTransformerService textTransformerService) {
        this.textTransformerService = textTransformerService;
    }

    /**
     * Collects request and returns response from service
     * <p>
     * User's request is collected, transformed and sent back as response
     * in the form of transformed text
     *
     * @param inputPayload Request class instance contained text and list of transformations
     * @return Response with transformed text
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Response post(@RequestBody Request inputPayload) throws DomainException {
        String text = inputPayload.getText();
        String[] transforms = inputPayload.getTransformations();

        logger.debug("Received request with: \n" +
                "Text: " + text + "\n" +
                "Transformations: " + Arrays.toString(transforms));

        if (text == null)
            throw new EmptyTextException();
        if (transforms == null)
            throw new EmptyTransformationsException();

        String transformedText = textTransformerService.getTranformedText(text, transforms);

        return new Response(transformedText);
    }

    /**
     * Endpoint with available transformations
     *
     * @return Set of available transformations
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Set<String> availableTransformations() {
        logger.debug("Received request for options");
        return textTransformerService.getAvailableTransformationsNames();
    }
}
