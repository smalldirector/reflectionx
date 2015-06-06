package org.reflectionx.data;

import org.reflectionx.analyzer.ClassType;
import org.reflectionx.data.annotations.ClassAnnotationA;
import org.reflectionx.data.annotations.ClassAnnotationB;
import org.reflectionx.data.annotations.FieldAnnotationA;
import org.reflectionx.data.annotations.FieldAnnotationB;
import org.reflectionx.data.annotations.MethodAnnotationA;
import org.reflectionx.data.annotations.MethodAnnotationB;
import org.reflectionx.data.annotations.MethodParameterAnnotationA;
import org.reflectionx.data.classes.ClassA;
import org.reflectionx.data.classes.ClassB;
import org.reflectionx.data.classes.ClassC;
import org.reflectionx.data.classes.ClassD;
import org.reflectionx.data.classes.ClassE;
import org.reflectionx.data.enums.EnumA;
import org.reflectionx.data.exceptions.ExceptionA;
import org.reflectionx.data.exceptions.ExceptionB;
import org.reflectionx.data.interfaces.InterfaceA;
import org.reflectionx.data.interfaces.InterfaceB;
import org.reflectionx.data.interfaces.InterfaceC;
import org.reflectionx.data.superclass.SuperClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ClassAnnotationA(value = ClassA.class, colors = {"green", "yellow"}, order = 1, type = ClassType.GENERIC)
@ClassAnnotationB(age = 10)
@ClassAnnotationB(age = 20)
public class TestClass extends SuperClass implements InterfaceA<ClassD>, InterfaceB {

    public static final String DEFAULT_STRING = "hello world!";
    protected static final double DEFAULT_DOUBLE = 10.01;
    private static final EnumA DEFAULT_ENUM_A_VALUE = EnumA.VAL_A;
    private static final Set<ClassC> DEFAULT_SET = new HashSet<>();
    private static final ClassE[] DEFAULT_CLASS_E = new ClassE[]{new ClassE()};

    @FieldAnnotationA(order = 5)
    private ClassB id;

    @FieldAnnotationB(order = 0)
    @FieldAnnotationB(order = 1)
    private String name;

    private int age;

    private List<ClassC> list = new ArrayList<>();

    @Override
    @MethodAnnotationA(name = "add_entity")
    public void addEntity(List<ClassD> entities) {
        ClassE classE = new ClassE("123", new EnumA[]{EnumA.VAL_B});
        classE.extract(new ClassD());
        new ClassD().speak(new ArrayList<>());
        String[] strs = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entities.forEach(classE::extract);
        try {
          throw new ExceptionB();
        } catch (ExceptionB e) {
        }
    }

    @MethodAnnotationB
    @MethodAnnotationB(order = 1)
    public Map<ClassE, ClassD> run(ClassA a, @MethodParameterAnnotationA(name = "test_b") ClassB b, ClassC c, ClassD d)
            throws ExceptionA {
        ClassD[] classDs = new ClassD[]{};
        try {
            System.out.println(DEFAULT_STRING);
            System.out.println(DEFAULT_ENUM_A_VALUE);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class InnerClass implements InterfaceC {
    }
}
