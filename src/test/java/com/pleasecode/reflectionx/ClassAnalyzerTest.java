package com.pleasecode.reflectionx;

import org.junit.Before;
import org.junit.Test;
import com.pleasecode.reflectionx.analyzer.ClassAnalyzer;
import com.pleasecode.reflectionx.analyzer.ClassAnalyzerException;
import com.pleasecode.reflectionx.analyzer.ClassAnalyzerImpl;
import com.pleasecode.reflectionx.analyzer.ClassDependency;

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
        is = ClassAnalyzerTest.class.getResourceAsStream("/com/pleasecode/reflectionx/data/TestClass.class");
    }

    @Test
    public void testAnalyzeWithoutFilter() throws ClassAnalyzerException {
        final ClassDependency classDependency = classAnalyzer.analyze(is, null);
        assertNotNull(classDependency);
        System.out.println(classDependency);
        // base class
        assertThat(classDependency.getBaseClass(), is("com.pleasecode.reflectionx.data.TestClass"));
        // super class
        assertThat(classDependency.getSuperClass(), is("com.pleasecode.reflectionx.data.superclass.SuperClass"));
        // interfaces
        assertThat(classDependency.getInterfaces(), hasItems("com.pleasecode.reflectionx.data.interfaces.InterfaceA",
                "com.pleasecode.reflectionx.data.interfaces.InterfaceB"));
        // class annotations
        assertThat(classDependency.getClassAnnotations(), hasItems("com.pleasecode.reflectionx.data.annotations.ClassAnnotationA",
                "com.pleasecode.reflectionx.data.annotations.ClassAnnotationB", "com.pleasecode.reflectionx.data.annotations.ClassAnnotationBs"));
        // field annotations
        assertThat(classDependency.getFieldAnnotations(), hasItems("com.pleasecode.reflectionx.data.annotations.FieldAnnotationA",
                "com.pleasecode.reflectionx.data.annotations.FieldAnnotationB", "com.pleasecode.reflectionx.data.annotations.FieldAnnotationBs"));
        // method annotations
        assertThat(classDependency.getMethodAnnotations(), hasItems("com.pleasecode.reflectionx.data.annotations.MethodAnnotationA",
                "com.pleasecode.reflectionx.data.annotations.MethodAnnotationB", "com.pleasecode.reflectionx.data.annotations.MethodAnnotationBs"));
        // some of class dependencies
        assertThat(classDependency.getDependencies(), hasItems("com.pleasecode.reflectionx.data.classes.ClassA",
                "com.pleasecode.reflectionx.data.classes.ClassB", "com.pleasecode.reflectionx.data.classes.ClassC",
                "com.pleasecode.reflectionx.data.classes.ClassD", "com.pleasecode.reflectionx.data.classes.ClassE",
                "com.pleasecode.reflectionx.data.exceptions.ExceptionA"));
        assertFalse(classDependency.getDependencies().contains("com.pleasecode.reflectionx.data.exceptions.ExceptionC"));
    }
}
