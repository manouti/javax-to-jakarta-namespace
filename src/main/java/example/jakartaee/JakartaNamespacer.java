package example.jakartaee;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;

public class JakartaNamespacer {

    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("example.jakartaee.JakartaEESample");
        
        ClassFile classFile = ctClass.getClassFile();

        ConstPool constPool = classFile.getConstPool();
        constPool.renameClass("javax/json/JsonString", "jakarta/json/JsonString");
        constPool.renameClass("javax/json/JsonValue", "jakarta/json/JsonValue");

        updateField(classFile);

        // we can even update a local variable within the method itself, even though constPool.renameClass above covers it.
//        int newDescIndex = constPool.addUtf8Info("Ljakarta/json/JsonValue;");
//        updateLocalVar(classFile, newDescIndex);
        
        classFile.write(new DataOutputStream(new FileOutputStream(new File("target/classes/example/jakartaee/JakartaEESample.class"))));
        
        CtClass ctClass2 = classPool.get("example.jakartaee.MyJsonString");
        ClassFile classFile2 = ctClass2.getClassFile();
        ConstPool constPool2 = classFile2.getConstPool();
        constPool2.renameClass("javax/json/JsonString", "jakarta/json/JsonString");
        constPool2.renameClass("javax/json/JsonValue$ValueType", "jakarta/json/JsonValue$ValueType");
        MethodInfo getValueTypeMethod = classFile2.getMethod("getValueType");
        getValueTypeMethod.setDescriptor("()Ljakarta/json/JsonValue$ValueType;");
        classFile2.write(new DataOutputStream(new FileOutputStream(new File("target/classes/example/jakartaee/MyJsonString.class"))));
    }

    private static void updateField(ClassFile classFile) {
        FieldInfo fieldInfo = classFile.getFields().get(0);
        fieldInfo.setDescriptor("Ljakarta/json/JsonValue;");
    }

//  private static void updateLocalVar(ClassFile classFile, int newDescIndex) {
//  MethodInfo mainMethod = classFile.getMethod("main");
//  LocalVariableAttribute lvt = (LocalVariableAttribute) mainMethod.getCodeAttribute().getAttribute("LocalVariableTable");
//  LocalVariableAttribute newLvt = new LocalVariableAttribute(classFile.getConstPool());
//  newLvt.addEntry(lvt.startPc(0), lvt.codeLength(0),
//          lvt.nameIndex(0), lvt.descriptorIndex(0), lvt.index(0));
//  newLvt.addEntry(lvt.startPc(1), lvt.codeLength(1),
//          lvt.nameIndex(1), newDescIndex, lvt.index(1));
//  lvt.set(newLvt.get());
//  System.out.println(lvt.descriptor(1));
//}

}
