package com.pleasecode.reflectionx.analyzer;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code ClassDependency} class represents all referenced Java classes inside the special Java class.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassDependency {
    private static final String OBJECT_CLASS = "java.lang.Object";
    // class itself
    private String baseClass;
    private String superClass;
    private Set<String> interfaces = new HashSet<>();
    private Set<String> classAnnotations = new HashSet<>();
    private Set<String> fieldAnnotations = new HashSet<>();
    private Set<String> methodAnnotations = new HashSet<>();
    // all referenced Java classes except base class
    private Set<String> dependencies = new HashSet<>();

    public String getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(String baseClass) {
        this.baseClass = baseClass;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        if (OBJECT_CLASS.equals(superClass)) {
            return;
        }
        this.superClass = superClass;
        this.addDependency(superClass);
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public void addInterface(String _interface) {
        this.interfaces.add(_interface);
        this.addDependency(_interface);
    }

    public Set<String> getClassAnnotations() {
        return classAnnotations;
    }

    public void addClassAnnotations(String classAnnotation) {
        this.classAnnotations.add(classAnnotation);
        this.addDependency(classAnnotation);
    }

    public Set<String> getFieldAnnotations() {
        return fieldAnnotations;
    }

    public void addFieldAnnotations(String fieldAnnotation) {
        this.fieldAnnotations.add(fieldAnnotation);
        this.addDependency(fieldAnnotation);
    }

    public Set<String> getMethodAnnotations() {
        return methodAnnotations;
    }

    public void addMethodAnnotation(String methodAnnotation) {
        this.methodAnnotations.add(methodAnnotation);
        this.addDependency(methodAnnotation);
    }

    public Set<String> getDependencies() {
        return dependencies;
    }

    public void addDependency(String dependency) {
        if (baseClass != null && baseClass.equals(dependency)) {
            return;
        }
        this.dependencies.add(dependency);
    }

    @Override
    public String toString() {
        return "ClassDependency{" +
                "baseClass='" + baseClass + '\'' +
                ", superClass='" + superClass + '\'' +
                ", interfaces=" + interfaces +
                ", classAnnotations=" + classAnnotations +
                ", fieldAnnotations=" + fieldAnnotations +
                ", methodAnnotations=" + methodAnnotations +
                ", dependencies=" + dependencies +
                '}';
    }
}
