package DataStructuresAndAlgorithmAnalysisThirdEdition;

import java.util.*;

public class MyTree {

    private TreeNode head;

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer data;


        public TreeNode(TreeNode left, TreeNode right, Integer data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }

    public TreeNode getHead() {
        return head;
    }

    //按照层填充生成一颗树
    public void createTree(Integer[] nums) {
        int size = nums.length;
        List<TreeNode> nodes = new ArrayList();
        for (Integer num : nums) {
            if (num == null)
                nodes.add(null);
            else
                nodes.add(new TreeNode(null, null, num));
        }
        for (int i = 0; i < size / 2 + 1; i++) {
            if (2 * i + 1 < size && nodes.get(2 * i + 1) != null)
                nodes.get(i).left = nodes.get(2 * i + 1);
            if (2 * i + 2 < size && nodes.get(2 * i + 2) != null)
                nodes.get(i).right = nodes.get(2 * i + 2);
        }
        head = nodes.get(0);
    }

    //先序遍历树
    public void preTraverseTree(TreeNode head) {
        if (head != null) {
            System.out.println(head.data);
            preTraverseTree(head.left);
            preTraverseTree(head.right);
        }
    }

    //中序遍历树
    public void inTraverseTree(TreeNode head) {
        if (head != null) {
            preTraverseTree(head.left);
            System.out.println(head.data);
            preTraverseTree(head.right);
        }
    }

    //后序遍历树
    public void postTraverseTree(TreeNode head) {
        if (head != null) {
            preTraverseTree(head.left);
            preTraverseTree(head.right);
            System.out.println(head.data);
        }
    }

    //按层次遍历树
    public void levelTraverseTres(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.println(temp.data);
            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
    }

    //按层次遍历树，并且打印层级关系

}
