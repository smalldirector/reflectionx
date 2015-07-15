package com.pleasecode.reflectionx.data;

import com.pleasecode.reflectionx.analyzer.ClassType;
import com.pleasecode.reflectionx.data.annotations.ClassAnnotationA;
import com.pleasecode.reflectionx.data.annotations.FieldAnnotationB;
import com.pleasecode.reflectionx.data.annotations.MethodAnnotationA;
import com.pleasecode.reflectionx.data.annotations.MethodParameterAnnotationA;
import com.pleasecode.reflectionx.data.classes.ClassC;
import com.pleasecode.reflectionx.data.classes.ClassD;
import com.pleasecode.reflectionx.data.classes.ClassE;
import com.pleasecode.reflectionx.data.exceptions.ExceptionA;
import com.pleasecode.reflectionx.data.exceptions.ExceptionB;
import com.pleasecode.reflectionx.data.interfaces.InterfaceB;
import com.pleasecode.reflectionx.data.superclass.SuperClass;
import com.pleasecode.reflectionx.data.annotations.ClassAnnotationA;
import com.pleasecode.reflectionx.data.annotations.ClassAnnotationB;
import com.pleasecode.reflectionx.data.annotations.FieldAnnotationA;
import com.pleasecode.reflectionx.data.annotations.FieldAnnotationB;
import com.pleasecode.reflectionx.data.annotations.MethodAnnotationA;
import com.pleasecode.reflectionx.data.annotations.MethodAnnotationB;
import com.pleasecode.reflectionx.data.annotations.MethodParameterAnnotationA;
import com.pleasecode.reflectionx.data.classes.ClassA;
import com.pleasecode.reflectionx.data.classes.ClassB;
import com.pleasecode.reflectionx.data.classes.ClassC;
import com.pleasecode.reflectionx.data.classes.ClassD;
import com.pleasecode.reflectionx.data.classes.ClassE;
import com.pleasecode.reflectionx.data.enums.EnumA;
import com.pleasecode.reflectionx.data.exceptions.ExceptionA;
import com.pleasecode.reflectionx.data.exceptions.ExceptionB;
import com.pleasecode.reflectionx.data.interfaces.InterfaceA;
import com.pleasecode.reflectionx.data.interfaces.InterfaceB;
import com.pleasecode.reflectionx.data.interfaces.InterfaceC;
import com.pleasecode.reflectionx.data.superclass.SuperClass;

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
