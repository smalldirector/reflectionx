package org.reflectionx.analyzer;


import java.io.InputStream;

/**
 * A analyzer to analysis all referenced Java class information in the special class.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public interface ClassAnalyzer {

    /**
     * Analyze class dependencies of the special class with class filter condition.
     *
     * @param is     class input stream
     * @param filter class filter condition
     * @return class dependencies
     * @throws ClassAnalyzerException exception
     */
    ClassDependency analyze(InputStream is, ClassFilter filter) throws ClassAnalyzerException;
}
