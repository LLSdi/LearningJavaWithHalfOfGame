package MyFirstAnnotation;

import sun.security.krb5.internal.crypto.Des;

import java.lang.reflect.Method;

/**
 * @author halfOfGame
 * @create 2020-03-21,9:03
 */
public class ParseDescription {

    public static void main(String[] args) {

        try {
            Class c = Class.forName("MyFirstAnnotation.Test");
            boolean IsExist = c.isAnnotationPresent(Description.class);
            if (IsExist) {
                Description description = (Description)c.getAnnotation(Description.class);
                System.out.println(description.desc());
            }

            Method[] methods = c.getMethods();
            for (Method method : methods) {
                IsExist = method.isAnnotationPresent(Description.class);
                if (IsExist) {
                    Description description = method.getAnnotation(Description.class);
                    System.out.println(description.desc());
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
