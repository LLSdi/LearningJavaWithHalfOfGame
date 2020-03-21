package MyFirstAnnotation;

import java.lang.annotation.*;

/**
 * @author halfOfGame
 * @create 2020-03-21,9:00
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {

    String desc();

    String author();

    int age() default 21;
}
