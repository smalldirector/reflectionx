package org.reflectionx.asm;

import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;

/**
 * A helper to help analysis class signature, method signature and type signature information.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassSignatureHelper {

    public static void addTypeSignature(String signature, SignatureVisitor signatureVisitor) {
        if (signature == null || signatureVisitor == null) {
            return;
        }
        new SignatureReader(signature).acceptType(signatureVisitor);
    }

    public static void addSignature(String signature, SignatureVisitor signatureVisitor) {
        if (signature == null || signatureVisitor == null) {
            return;
        }
        new SignatureReader(signature).accept(signatureVisitor);
    }
}
