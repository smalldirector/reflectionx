package org.reflectionx.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.reflectionx.analyzer.AnnotationSource;
import org.reflectionx.analyzer.ClassCollector;
import org.reflectionx.analyzer.ClassType;

/**
 * A visitor to visit a Java annotation, meanwhile it will collect referenced Java class information.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassAnnotationVisitor extends AnnotationVisitor {

    private final ClassCollector collector;
    private AnnotationSource source;

    public ClassAnnotationVisitor(ClassCollector collector) {
        super(Opcodes.ASM5);
        this.collector = collector;
    }

    @Override
    public void visit(String name, Object value) {
        if (value instanceof Type) {
            collector.collect((Type) value);
        }
    }

    @Override
    public void visitEnum(String name, String desc, String value) {
        collector.collect(Type.getType(desc));
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        switch (source) {
            case CLASS: {
                collector.collect(Type.getType(desc), ClassType.CLASS_ANNOTATION);
                break;
            }
            case FIELD: {
                collector.collect(Type.getType(desc), ClassType.FIELD_ANNOTATION);
                break;
            }
            case METHOD: {
                collector.collect(Type.getType(desc), ClassType.METHOD_ANNOTATION);
                break;
            }
            default: {
                collector.collect(Type.getType(desc));
            }
        }
        return this;
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        return this;
    }

    public AnnotationSource getSource() {
        return source;
    }

    public void setSource(AnnotationSource source) {
        this.source = source;
    }
}
