package lab12.ex03;

import java.io.Serializable;

import java.lang.reflect.*;
import java.lang.*;

public class PDSSerializer implements Serializable {
    public static String fromObject(Object o){

    Class<?> cl = o.getClass();

    if (isWrapperType(cl) || o instanceof String) {
        return String.valueOf(o);
    }
        

    if (cl.isArray()) {
        int length = Array.getLength(o);

        StringBuilder ret = new StringBuilder("[");
        for (int i=0; i < length; i++) {
            ret.append(fromObject(Array.get(o, i)));
            if (i != length -1)
                ret.append(", ");
        }
        ret.append("]");
        return ret.toString();
    }

    Field[] fields = cl.getDeclaredFields();
    Method[] methods = cl.getDeclaredMethods();
    StringBuilder ret = new StringBuilder();

    for (Field f : fields) {
        f.setAccessible(true);
        Object value = null;

        String att = f.getName();
        att = "get" + Character.toUpperCase(att.charAt(0)) + att.substring(1);
        try {
            Method method = cl.getMethod(att);
            value = fromObject(method.invoke(o));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Class<?> type = f.getType();
        ret.append(f.getName().substring(0, 1).toUpperCase()).append(f.getName().substring(1)).append(": ");


        if (!isWrapperType(type) && !type.isPrimitive() && !type.isArray()) {
            ret.append("{\n");
        }
        ret.append(value);
        if (!isWrapperType(type) && !type.isPrimitive() && !type.isArray()) {
            ret.append("}");
        }
        ret.append("\n");
        f.setAccessible(false);
    }


        //Explore os metodos
        //cl.getMethods();
        //cl.getFields();
        //Veja o javadoc das classes: Class, Method, Field, Modifier
        return ret.toString();
    }

    public static boolean isWrapperType(Class<?> cls) {
        return cls.equals(Boolean.class) || 
            cls.equals(Integer.class) ||
            cls.equals(Character.class) ||
            cls.equals(Byte.class) ||
            cls.equals(Short.class) ||
            cls.equals(Double.class) ||
            cls.equals(Long.class) ||
            cls.equals(Float.class) ||
            cls.equals(String.class);
    }
}
    