package dev.valvar.orangehrm.listener;

import dev.valvar.orangehrm.utils.Retry;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Трансформер аннотаций.
 */
public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod
    ) {
        annotation.setRetryAnalyzer(Retry.class);
    }

}
