import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * @author halfOfGame
 * @create 2020-04-30,16:13
 */
public class Test_3 {
    public static void main(String[] args) {
        int length = 11 ;
        for (int sz = 1; sz < length; sz = sz + sz) {
            for (int lo = 0; lo < length - sz; lo += sz + sz) {
                System.out.println(lo + "," + (lo + sz - 1) + "," + Math.min(lo + sz + sz - 1, length - 1));
            }
        }
    }
}
