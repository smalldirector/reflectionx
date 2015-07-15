package com.pleasecode.reflectionx.analyzer;

import org.objectweb.asm.Type;

import java.util.Arrays;

/**
 * A collector to collect all referenced Java class information of one special Java class.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassCollector {

    private final ClassDependency classDependency = new ClassDependency();

    private final ClassFilter filter;

    public ClassCollector(ClassFilter filter) {
        this.filter = filter;
    }

    public void collect(String className) {
        collect(className, ClassType.GENERIC);
    }

    public void collect(String[] classNames) {
        collect(classNames, ClassType.GENERIC);
    }

    public void collect(String[] classNames, ClassType classType) {
        if (classNames == null) {
            return;
        }
        Arrays.stream(classNames).forEach(className -> collect(className, classType));
    }

    public void collect(Type type) {
        collect(type, ClassType.GENERIC);
    }

    public void collect(Type type, ClassType classType) {
        if (type == null) {
            return;
        }
        switch (type.getSort()) {
            case Type.ARRAY: {
                collect(type.getElementType().getClassName(), classType);
                break;
            }
            case Type.OBJECT: {
                collect(type.getClassName(), classType);
                break;
            }
        }
    }

    public void collect(String className, ClassType classType) {
        if (className == null || className.isEmpty() || classType == null) {
            return;
        }

        className = convertClassName(className);
        // filter class
        if (filter != null && filter.getExcludes().contains(className)) {
            return;
        }

        switch (classType) {
            case BASE_CLASS: {
                classDependency.setBaseClass(className);
                break;
            }
            case SUPER_CLASS: {
                classDependency.setSuperClass(className);
                break;
            }
            case INTERFACE: {
                classDependency.addInterface(className);
                break;
            }
            case CLASS_ANNOTATION: {
                classDependency.addClassAnnotations(className);
                break;
            }
            case FIELD_ANNOTATION: {
                classDependency.addFieldAnnotations(className);
                break;
            }
            case METHOD_ANNOTATION: {
                classDependency.addMethodAnnotation(className);
                break;
            }
            case GENERIC: {
                classDependency.addDependency(className);
            }
        }
    }

    public ClassDependency getClassDependency() {
        return classDependency;
    }

    private String convertClassName(String className) {
        if (className.startsWith("[L") && className.endsWith(",")) {
            className.substring(2, className.length() - 1);
        }
        return className.replace('/', '.');
    }
}
