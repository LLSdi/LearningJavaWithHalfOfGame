import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * @author halfOfGame
 * @create 2020-04-30,16:13
 */
public class Test_3 {
    public static void main(String[] args) {
        System.out.println("--------------");
        assert testAssert(2);
        System.out.println("--------------");
    }
    public static boolean testAssert(int num){
        int a = 1;
        return a == num ? true : false;
    }
}
