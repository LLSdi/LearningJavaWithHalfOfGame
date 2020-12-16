//思路，先模拟一下，中序遍历建立一棵完全二叉搜索树

import java.util.Arrays;
import java.util.Scanner;

public class Main69 {
    static int n;
    static int[] node;
    //此时过样例
    //static int[]tree=new int[11];
    //此时不过,因为一开始n默认为0,所以经验就是一开始不开大小的比如Queue和Stack可以在一开始就定义好；
    //数组则不可以。
    //static int[]tree=new int[n+1];
    static int[] tree;
    static int k = 1;

    public static void ldr(int root) {
        if (root > n) return;
        else {
            ldr(2 * root);
            tree[root] = node[k++];
            ldr(2 * root + 1);
        }
    }

    public static void level() {
        System.out.print(tree[1]);
        for (int i = 2; i <= n; i++) {
            System.out.print(" " + tree[i]);
        }
    }

    public static void main(String[] args) {
        n = 10;
        node = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        tree = new int[n + 1];
        int root = 1;
        ldr(root);
        level();

    }
}