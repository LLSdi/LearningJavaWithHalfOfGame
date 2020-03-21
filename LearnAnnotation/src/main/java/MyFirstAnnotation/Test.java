package MyFirstAnnotation;

/**
 * @author halfOfGame
 * @create 2020-03-21,9:03
 */

@Description(desc = "Hello world", author = "halfOfGame")
public class Test {

    @Description(desc = "Hello Annotaion", author = "halfOfGame")
    public void Hello() {
        System.out.println("Hello halfOfGame");
    }
}
