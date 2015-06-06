package org.reflectionx.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.reflectionx.analyzer.AnnotationSource;
import org.reflectionx.analyzer.ClassCollector;
import org.reflectionx.analyzer.ClassType;

import java.util.Arrays;

/**
 * A visitor to visit a Java class, meanwhile it will collect referenced Java class information.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassFileVisitor extends ClassVisitor {

    private final ClassSignatureVisitor signatureVisitor;
    private final ClassAnnotationVisitor annotationVisitor;
    private final ClassFieldVisitor fieldVisitor;
    private final ClassMethodVisitor methodVisitor;
    private final ClassCollector collector;

    public ClassFileVisitor(ClassSignatureVisitor signatureVisitor, ClassAnnotationVisitor annotationVisitor,
                            ClassFieldVisitor fieldVisitor, ClassMethodVisitor methodVisitor, ClassCollector collector) {
        super(Opcodes.ASM5);
        this.signatureVisitor = signatureVisitor;
        this.annotationVisitor = annotationVisitor;
        this.fieldVisitor = fieldVisitor;
        this.methodVisitor = methodVisitor;
        this.collector = collector;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        collector.collect(name, ClassType.BASE_CLASS);
        collector.collect(superName, ClassType.SUPER_CLASS);
        collector.collect(interfaces, ClassType.INTERFACE);
        if (signature != null) {
            ClassSignatureHelper.addSignature(signature, signatureVisitor);
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        annotationVisitor.setSource(AnnotationSource.CLASS);
        collector.collect(Type.getType(desc), ClassType.CLASS_ANNOTATION);
        return annotationVisitor;
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        collector.collect(name);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (signature == null) {
            collector.collect(Type.getType(desc));
        } else {
            ClassSignatureHelper.addTypeSignature(signature, signatureVisitor);
        }
        return fieldVisitor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (signature == null) {
            collector.collect(Type.getReturnType(desc));
            final Type[] types = Type.getArgumentTypes(desc);
            Arrays.stream(types).forEach(collector::collect);
        } else {
            ClassSignatureHelper.addSignature(signature, signatureVisitor);
        }
        collector.collect(exceptions);
        return methodVisitor;
    }
}
