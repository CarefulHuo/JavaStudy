package cf.carefulhuo.thread.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 获取 TargetClass 的 Class 对象，并获取它的实例对象
        Class<?> targetClass = Class.forName("cf.carefulhuo.thread.reflection.TargetClass");
        TargetClass targetObject = (TargetClass) targetClass.getDeclaredConstructor().newInstance();

        // 获取 targetObject 中定义的所有方法(声明的方法)
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取指定方法，并进行调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "Huowy");

        // 获取指定参数并对其进行修改
        Field field = targetClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(targetObject, "Huowy");
        System.out.println(field.get(targetObject));

        // 调用私有方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }
}
