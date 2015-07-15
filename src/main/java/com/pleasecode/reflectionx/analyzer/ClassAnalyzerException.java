package com.pleasecode.reflectionx.analyzer;

/**
 * The {@code ClassAnalyzerException} class represents the exceptions when {@link ClassAnalyzer} analyzes a Java class.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassAnalyzerException extends Exception {
    private static final long serialVersionUID = 3256989115832383325L;

    public ClassAnalyzerException(String message) {
        super(message);
    }

    public ClassAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }
}
