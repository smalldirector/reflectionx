package org.reflectionx.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.reflectionx.analyzer.AnnotationSource;
import org.reflectionx.analyzer.ClassCollector;
import org.reflectionx.analyzer.ClassType;

/**
 * A visitor to visit a Java method, meanwhile it will collect referenced Java class information.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassMethodVisitor extends MethodVisitor {

    private final ClassAnnotationVisitor annotationVisitor;
    private final ClassSignatureVisitor signatureVisitor;
    private final ClassCollector collector;

    public ClassMethodVisitor(ClassAnnotationVisitor annotationVisitor, ClassSignatureVisitor signatureVisitor,
                              ClassCollector collector) {
        super(Opcodes.ASM5);
        this.annotationVisitor = annotationVisitor;
        this.signatureVisitor = signatureVisitor;
        this.collector = collector;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        annotationVisitor.setSource(AnnotationSource.METHOD);
        collector.collect(Type.getType(desc), ClassType.METHOD_ANNOTATION);
        return annotationVisitor;
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
        collector.collect(Type.getType(desc), ClassType.METHOD_ANNOTATION);
        return annotationVisitor;
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if (type.charAt(0) == '[') {
            collector.collect(Type.getType(type));
        } else {
            collector.collect(type);
        }
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        collector.collect(owner);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        collector.collect(owner);
    }

    @Override
    public void visitLdcInsn(Object cst) {
        if (cst instanceof Type) {
            collector.collect((Type)cst);
        }
    }

    @Override
    public void visitMultiANewArrayInsn(String desc, int dims) {
        collector.collect(Type.getType(desc));
    }


    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        collector.collect(type);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        if (signature == null) {
            collector.collect(Type.getType(desc));
        } else {
            ClassSignatureHelper.addTypeSignature(signature, signatureVisitor);
        }
    }
}
