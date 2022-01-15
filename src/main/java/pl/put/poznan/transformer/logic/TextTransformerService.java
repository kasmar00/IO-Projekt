package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.exceptions.DomainException;
import pl.put.poznan.transformer.logic.exceptions.NoTransformationException;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextImpl;
import pl.put.poznan.transformer.logic.text.TextTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

@Service
public class TextTransformerService {
    private static final Logger logger = LoggerFactory.getLogger(TextTransformerService.class);

    private final HashMap<String, Class<TextTransformer>> availableTransformations = fetchAvailableTransformations();

    /**
     * Transform text using transformations
     * <p>
     * A decorated object is dynamically constructed (based on avaliable transformations).
     * Text with applied transformations is returned
     *
     * @param text            Text to be transformed
     * @param transformations List of transformations to be applied
     * @return transformed text
     */
    public String getTranformedText(String text, String[] transformations) throws DomainException {
        Text decorated = new TextImpl(text);

        for (String transformationName : transformations) {
            try {
                Class<TextTransformer> cls = availableTransformations.get(transformationName);
                Constructor<TextTransformer> cons = cls.getConstructor(Text.class);
                decorated = cons.newInstance(decorated);
            } catch (Throwable e) {
                logger.warn("Can't find transformer for type: " + transformationName, e);
                throw new NoTransformationException(transformationName);
            }
        }

        return decorated.transform();
    }

    /**
     * Searches for available decorator classes
     * <p>
     * Class path of pl.put.poznan.transformer.logic.text is searched for classes that extend TextTransformer class.
     * Such classes are asked for their static field name (which should contain a transformation name) and if one is found
     * are added to hashmap under that name.
     *
     * @return Hashmap of available transformations names to classes
     **/
    private HashMap<String, Class<TextTransformer>> fetchAvailableTransformations() {
        HashMap<String, Class<TextTransformer>> transformations = new HashMap<>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(TextTransformer.class));
        Set<BeanDefinition> components = provider.findCandidateComponents("pl/put/poznan/transformer/logic/text");
        for (BeanDefinition component : components) {
            try {
                Class<TextTransformer> cls = (Class<TextTransformer>) Class.forName(component.getBeanClassName());
                Field f = cls.getDeclaredField("name");
                transformations.put(f.get(null).toString(), cls);
            } catch (Throwable e) {
                logger.warn("Can't register class " + component.getBeanClassName() + " as avaliable tranformation", e);
            }
        }

        logger.info("Available transformations are: " + transformations);
        return transformations;
    }

    public Set<String> getAvailableTransformationsNames() {
        return availableTransformations.keySet();
    }
}
