package org.reflectionx.data.annotations;

import org.reflectionx.analyzer.ClassType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassAnnotationA {
    Class<?> value();
    String[] colors();
    int order() default 0;
    ClassType type();
}
