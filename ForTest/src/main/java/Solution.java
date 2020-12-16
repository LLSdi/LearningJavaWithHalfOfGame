import java.util.HashMap;
import java.util.Map;
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
//    int[] pre;
//    Map<Integer, Integer> inMap = new HashMap();
//
//    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
//        if(pre == null || in == null || pre.length == 0 || in.length == 0) {
//            return null;
//        }
//        this.pre = pre;
//        for(int i = 0; i < in.length; i++) {
//            inMap.put(in[i], i);
//        }
//        return reConstructBinaryTreeHelper(0, 0, in.length - 1);
//    }
//
//    private TreeNode reConstructBinaryTreeHelper(int preIndex, int inLeftIndex, int inRightIndex) {
//        if(inLeftIndex > inRightIndex) {
//            return null;
//        }
//        TreeNode temporaryNode = new TreeNode(pre[preIndex]);
//        int inRootIndex = inMap.get(pre[preIndex]);
//        temporaryNode.left = reConstructBinaryTreeHelper(preIndex + 1, inLeftIndex, inRootIndex - 1);
//        temporaryNode.right = reConstructBinaryTreeHelper((preIndex + 1 + inRootIndex - 1 - inLeftIndex + 1), inRootIndex + 1, inRightIndex);
//        return temporaryNode;
//    }
}