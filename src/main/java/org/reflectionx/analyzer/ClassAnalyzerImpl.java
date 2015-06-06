package org.reflectionx.analyzer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.reflectionx.asm.ClassAnnotationVisitor;
import org.reflectionx.asm.ClassFieldVisitor;
import org.reflectionx.asm.ClassFileVisitor;
import org.reflectionx.asm.ClassMethodVisitor;
import org.reflectionx.asm.ClassSignatureVisitor;

import java.io.IOException;
import java.io.InputStream;

/**
 * The implement class for {@link ClassAnalyzer}
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassAnalyzerImpl implements ClassAnalyzer {

    @Override
    public ClassDependency analyze(InputStream is, ClassFilter filter) throws ClassAnalyzerException {
        if (is == null) {
            throw new ClassAnalyzerException("The input stream of class file is empty!");
        }
        try {
            final ClassReader reader = new ClassReader(is);
            final ClassCollector collector = new ClassCollector(filter);
            final ClassSignatureVisitor signatureVisitor = new ClassSignatureVisitor(collector);
            final ClassAnnotationVisitor annotationVisitor = new ClassAnnotationVisitor(collector);
            final ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(annotationVisitor, collector);
            final ClassMethodVisitor methodVisitor = new ClassMethodVisitor(annotationVisitor, signatureVisitor, collector);
            final ClassVisitor classVisitor =
                    new ClassFileVisitor(signatureVisitor, annotationVisitor, fieldVisitor, methodVisitor, collector);
            reader.accept(classVisitor, 0);
            return collector.getClassDependency();
        } catch (IOException e) {
            throw new ClassAnalyzerException("Failed to analyze this Java class!", e);
        }
    }
}
