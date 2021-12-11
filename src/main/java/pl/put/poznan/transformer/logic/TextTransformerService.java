package pl.put.poznan.transformer.logic;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.text.Text;
import pl.put.poznan.transformer.logic.text.TextImpl;
import pl.put.poznan.transformer.logic.text.TextTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

@Service
public class TextTransformerService {
    private final HashMap<String, Class> availableTransformations = getAvailableTransformations();

    public String getTranformedText(String text, String[] transformations) {
        Text decorated = new TextImpl(text);

        for (String transformationName : transformations) {
            try {
                System.out.println(transformationName);
                Class cls = availableTransformations.get(transformationName);
                Constructor<Text> cons = cls.getConstructor(Text.class);
                decorated = cons.newInstance(decorated);
            } catch (Throwable e) {
//                log
            }
        }

        return decorated.transform();
    }

    private HashMap<String, Class> getAvailableTransformations() {
        HashMap<String, Class> transformations = new HashMap<>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(TextTransformer.class));
        Set<BeanDefinition> components = provider.findCandidateComponents("pl/put/poznan/transformer/logic/text");
        for (BeanDefinition component : components) {
            try {
                Class<?> cls = Class.forName(component.getBeanClassName());
                System.out.println(cls.getName());
                Field f = cls.getDeclaredField("name");
                transformations.put(f.get(null).toString(), cls);
            } catch (Throwable e) {
            }
        }

        System.out.println(transformations);
        return transformations;
    }
}
