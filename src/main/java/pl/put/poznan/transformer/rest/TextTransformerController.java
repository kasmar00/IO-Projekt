package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerService;

import java.util.Arrays;

/**
 * Text transformer controller class
 * <p>
 * Rest controller class to text transformations
 */
@RestController
@RequestMapping("/transform")
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
    public Response post(@RequestBody Request inputPayload) {
        String text = inputPayload.getText();
        String[] transforms = inputPayload.getTransformations();

        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        String transformedText = textTransformerService.getTranformedText(text, transforms);

        return new Response(transformedText);
    }
}
