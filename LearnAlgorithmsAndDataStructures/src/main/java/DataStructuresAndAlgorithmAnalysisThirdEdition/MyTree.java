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

    public Integer getData(TreeNode node) {
        if (node != null)
            return node.data;
        else
            return null;
    }

    public TreeNode getHead() {
        return head;
    }

    public TreeNode getLeft(TreeNode node) {
        if (node != null)
            return node.left;
        else
            return null;
    }

    public TreeNode getRight(TreeNode node) {
        if (node != null)
            return node.right;
        else
            return null;
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
    public void levelTraverseTree(TreeNode head) {
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


    /**
     *按层次遍历树，并且打印层级关系
     *last记录当前行的最右节点
     *nlast记录下一行的最右节点，每进入一个节点就刷新一次
     */
    public void levelTraverseTree_2(TreeNode head) {
        TreeNode last = head, nlast = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null){
                queue.add(temp.left);
                nlast = temp.left;
            }
            if (temp.right != null) {
                queue.add(temp.right);
                nlast = temp.right;
            }
            if (temp == last) {
                System.out.println();
                last = nlast;
            }
        }
    }

    /**
     * 返回p和q共同的祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q)
            return root;

        TreeNode leftNode=lowestCommonAncestor(root.left,p,q);
        TreeNode rightNode=lowestCommonAncestor(root.right,p,q);

        if(leftNode==null)
            return rightNode;
        if(rightNode==null)
            return leftNode;

        return root;
    }
}
