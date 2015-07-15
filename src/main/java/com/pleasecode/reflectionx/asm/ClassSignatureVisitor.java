package com.pleasecode.reflectionx.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;
import com.pleasecode.reflectionx.analyzer.ClassCollector;

/**
 * A visitor to visit a generic signature, meanwhile it will collect referenced Java class information.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassSignatureVisitor extends SignatureVisitor {

    private final ClassCollector collector;

    public ClassSignatureVisitor(ClassCollector collector) {
        super(Opcodes.ASM5);
        this.collector = collector;
    }

    @Override
    public void visitClassType(String name) {
        collector.collect(name);
    }

    @Override
    public void visitInnerClassType(String name) {
        collector.collect(name);
    }

}
