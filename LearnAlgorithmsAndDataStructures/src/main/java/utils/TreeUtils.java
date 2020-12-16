package utils;

import java.util.*;

public class TreeUtils {

    private static TreeNode head;

    public static TreeNode getHead() {
        return head;
    }

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer data;

        public TreeNode(TreeNode left, TreeNode right, Integer data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }


        public TreeNode(Integer data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }
    }

    //-------------------------------------------------
    //                   创建二叉树
    //-------------------------------------------------

    /**
     * 创建一颗普通的完全二叉树，
     *
     * @param length
     * @return
     */
    public static TreeNode createNormalTree(int length) {
        head = createNormalTreeHelper(length, 1);
        return head;
    }

    private static TreeNode createNormalTreeHelper(int length, int data) {
        if (data <= length) {
            TreeNode head = new TreeNode(data);
            head.left = createNormalTreeHelper(length, 2 * data);
            head.right = createNormalTreeHelper(length, 2 * data + 1);
            return head;
        } else {
            return null;
        }
    }

    /**
     * 根据给定的数据创建一颗二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createNormalTree(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[nums.length];
        for (int i = 0; i < nums.length; i++) {

        }
        for (int i = 0; i < nums.length; i++) {

        }
        return null;
    }


    /**
     * 根据给定的数据创建一颗平衡的二叉树，左右子树节点数相差最多为1
     *
     * @param nums
     * @return
     */
    public static TreeNode createSearchTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return createSearchTree(nums, 0, nums.length - 1);
    }

    private static TreeNode createSearchTree(int[] nums, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return null;
        }
        if (leftIndex == rightIndex) {
            return new TreeNode(nums[leftIndex]);
        }
        int middleIndex = (leftIndex + rightIndex + 1) / 2;
        TreeNode head = new TreeNode(nums[middleIndex]);
        head.left = createSearchTree(nums, leftIndex, middleIndex - 1);
        head.right = createSearchTree(nums, middleIndex + 1, rightIndex);
        return head;
    }


    static int[] pre;
    static Map<Integer, Integer> map = new HashMap();

    /**
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static TreeNode preOrderAndInOrderCreation(int[] preOrder, int[] inOrder) {
        pre = preOrder;
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        ArrayList<Integer> list = new ArrayList();
        return preOrderAndInOrderCreationHelper(0, 0, preOrder.length - 1);
    }

    private static TreeNode preOrderAndInOrderCreationHelper(int preIndex, int inLeftIndex, int inRightIndex) {
        if (inLeftIndex > inRightIndex) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex]);
        int inRootIndex = map.get(root.data);
        root.left = preOrderAndInOrderCreationHelper(preIndex + 1, inLeftIndex, inRootIndex - 1);
        root.right = preOrderAndInOrderCreationHelper(preIndex + 1 + (inRootIndex - 1 - inLeftIndex + 1), inRootIndex + 1, inRightIndex);
        return root;
    }


    //-------------------------------------------------
    //                   树的节点个数
    //-------------------------------------------------

    /**
     * 用递归求出树的节点个数
     *
     * @param head
     * @return
     */
    public static int getTotalNodeCountRecurtion(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return getTotalNodeCountRecurtion(head.left) + getTotalNodeCountRecurtion(head.right) + 1;
    }

    /**
     * 用迭代求树的节点个数
     *
     * @param head
     * @return
     */
    public static int getTotalNodeCountIteration(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode temporaryNode = stack.pop();
            count++;
            if (temporaryNode.right != null) {
                stack.push(temporaryNode.right);
            }
            if (temporaryNode.left != null) {
                stack.push(temporaryNode.left);
            }
        }
        return count;
    }

    //-------------------------------------------------
    //                   树的层数
    //-------------------------------------------------


    /**
     * 求树的层数
     *
     * @param head
     * @return
     */
    public static int getLevelCount(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(getLevelCount(head.left), getLevelCount(head.right)) + 1;
    }


    //-------------------------------------------------
    //                   树的前序遍历
    //-------------------------------------------------

    /**
     * 用递归实现前序遍历
     *
     * @param head
     */
    public static void preTraverseRecursion(TreeNode head) {
        if (head != null) {
            System.out.print(head.data + " ");
            preTraverseRecursion(head.left);
            preTraverseRecursion(head.right);
        }
    }

    /**
     * 用迭代实现前序遍历
     *
     * @param head
     */
    public static void preTraverseIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode temporaryNode = stack.pop();
            System.out.print(temporaryNode.data + " ");
            if (temporaryNode.right != null) {
                stack.push(temporaryNode.right);
            }
            if (temporaryNode.left != null) {
                stack.push(temporaryNode.left);
            }
        }
    }


    //-------------------------------------------------
    //                   树的中序遍历
    //-------------------------------------------------

    /**
     * 用递归实现中序遍历
     *
     * @param head
     */
    public static void inTraverseRecursion(TreeNode head) {
        if (head != null) {
            inTraverseRecursion(head.left);
            System.out.print(head.data + " ");
            inTraverseRecursion(head.right);
        }
    }


    //-------------------------------------------------
    //                   树的后序遍历
    //-------------------------------------------------

    /**
     * 用递归实现后序遍历
     *
     * @param head
     */
    public static void postTraverseRecursion(TreeNode head) {
        if (head != null) {
            postTraverseRecursion(head.left);
            postTraverseRecursion(head.right);
            System.out.print(head.data + " ");
        }
    }


    //-------------------------------------------------
    //                   树的层序遍历
    //-------------------------------------------------

    /**
     * 层序遍历二叉树，并打印出层次结构
     *
     * @param head
     */
    public static void levelTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(head);
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode temporaryNode = queue.remove();
                System.out.print(temporaryNode.data + " ");
                if (temporaryNode.left != null) {
                    queue.add(temporaryNode.left);
                }
                if (temporaryNode.right != null) {
                    queue.add(temporaryNode.right);
                }
            }
            System.out.println();
        }
    }


    //-------------------------------------------------
    //                   树的各种判断
    //-------------------------------------------------


    private static Long preNodeVal = Long.MIN_VALUE;
    /**
     * 判断是否是二叉搜索树
     * @param root
     * @return
     */
    public static boolean isSearchTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        boolean left = isSearchTree(root.left);
        if(root.data < preNodeVal) {
            return false;
        }
        boolean right = isSearchTree(root.right);
        preNodeVal = Long.valueOf(root.data);
        return left && right;
    }

    /**
     * 判断是否是
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if(root.left == null || root.right == null) {
            return false;
        }
        return isCompleteTree(root.left) && isCompleteTree(root.right);
    }


    //-------------------------------------------------
    //                   树的序列化和反序列化，层序
    //-------------------------------------------------

    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node != null) {
                result.append(node.data + ",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                result.append("null,");
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    static public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if (!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
