package org.reflectionx.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.reflectionx.analyzer.AnnotationSource;
import org.reflectionx.analyzer.ClassCollector;
import org.reflectionx.analyzer.ClassType;

/**
 * A visitor to visit a Java field, meanwhile it will collect referenced Java class information.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassFieldVisitor extends FieldVisitor {

    private final ClassAnnotationVisitor annotationVisitor;
    private final ClassCollector collector;

    public ClassFieldVisitor(ClassAnnotationVisitor annotationVisitor, ClassCollector collector) {
        super(Opcodes.ASM5);
        this.annotationVisitor = annotationVisitor;
        this.collector = collector;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        annotationVisitor.setSource(AnnotationSource.FIELD);
        collector.collect(Type.getType(desc), ClassType.FIELD_ANNOTATION);
        return annotationVisitor;
    }
}
