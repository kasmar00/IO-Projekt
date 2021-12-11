package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerService;

import java.util.Arrays;


@RestController
@RequestMapping("/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private TextTransformerService textTransformerService;

    public TextTransformerController(TextTransformerService textTransformerService) {
        this.textTransformerService = textTransformerService;
    }


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
