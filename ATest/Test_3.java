import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author halfOfGame
 * @create 2020-04-30,16:13
 */
public class Test_3 {
    public static void main(String[] args) {
        HashMap<Integer, String> integerStringHashMap = new HashMap<>();
        integerStringHashMap.put(1,"Hello");
        System.out.println(integerStringHashMap.get(1));
        integerStringHashMap.put(1,"World");
        System.out.println(integerStringHashMap.get(1));
    }
}
