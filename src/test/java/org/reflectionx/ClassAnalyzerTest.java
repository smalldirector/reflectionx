package org.reflectionx;

import org.junit.Before;
import org.junit.Test;
import org.reflectionx.analyzer.ClassAnalyzer;
import org.reflectionx.analyzer.ClassAnalyzerException;
import org.reflectionx.analyzer.ClassAnalyzerImpl;
import org.reflectionx.analyzer.ClassDependency;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ClassAnalyzerTest {

    private final ClassAnalyzer classAnalyzer = new ClassAnalyzerImpl();
    private InputStream is;

    @Before
    public void init() {
        is = ClassAnalyzerTest.class.getResourceAsStream("/org/reflectionx/data/TestClass.class");
    }

    @Test
    public void testAnalyzeWithoutFilter() throws ClassAnalyzerException {
        final ClassDependency classDependency = classAnalyzer.analyze(is, null);
        assertNotNull(classDependency);
        System.out.println(classDependency);
        // base class
        assertThat(classDependency.getBaseClass(), is("org.reflectionx.data.TestClass"));
        // super class
        assertThat(classDependency.getSuperClass(), is("org.reflectionx.data.superclass.SuperClass"));
        // interfaces
        assertThat(classDependency.getInterfaces(), hasItems("org.reflectionx.data.interfaces.InterfaceA",
                "org.reflectionx.data.interfaces.InterfaceB"));
        // class annotations
        assertThat(classDependency.getClassAnnotations(), hasItems("org.reflectionx.data.annotations.ClassAnnotationA",
                "org.reflectionx.data.annotations.ClassAnnotationB", "org.reflectionx.data.annotations.ClassAnnotationBs"));
        // field annotations
        assertThat(classDependency.getFieldAnnotations(), hasItems("org.reflectionx.data.annotations.FieldAnnotationA",
                "org.reflectionx.data.annotations.FieldAnnotationB", "org.reflectionx.data.annotations.FieldAnnotationBs"));
        // method annotations
        assertThat(classDependency.getMethodAnnotations(), hasItems("org.reflectionx.data.annotations.MethodAnnotationA",
                "org.reflectionx.data.annotations.MethodAnnotationB", "org.reflectionx.data.annotations.MethodAnnotationBs"));
        // some of class dependencies
        assertThat(classDependency.getDependencies(), hasItems("org.reflectionx.data.classes.ClassA",
                "org.reflectionx.data.classes.ClassB", "org.reflectionx.data.classes.ClassC",
                "org.reflectionx.data.classes.ClassD", "org.reflectionx.data.classes.ClassE",
                "org.reflectionx.data.exceptions.ExceptionA"));
        assertFalse(classDependency.getDependencies().contains("org.reflectionx.data.exceptions.ExceptionC"));
    }
}
