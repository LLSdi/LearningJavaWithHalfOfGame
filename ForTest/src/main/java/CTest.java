import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

public class CTest implements DTest {

    public static int[] findError() {
//        List<Integer> list = Arrays.asList(1,2,6,4,5,3,7);
//        List<Integer> list = Arrays.asList(1,2,3,5,4,6,7);
//        List<Integer> list = Arrays.asList(2,1,3,4,5,6,7);
//        List<Integer> list = Arrays.asList(3,2,1,4,5,6,7);
//        List<Integer> list = Arrays.asList(1,2,3,4,5,7,6);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 7, 6, 5);
        int[] result = new int[2];
        int i, j;
        for (i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                result[1] = list.get(i);
                break;
            }
        }
        for (j = list.size() - 1; j > i; j--) {
            if (list.get(j) < list.get(j - 1)) {
                result[0] = list.get(j);
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int v1 = Integer.parseInt("2");
        int v2 = Integer.parseInt("null");
        System.out.println(v1);
        System.out.println(v2);
    }
}
