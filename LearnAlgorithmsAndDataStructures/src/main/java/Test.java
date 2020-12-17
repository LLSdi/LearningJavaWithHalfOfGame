import sun.reflect.generics.tree.Tree;
import utils.TreeUtils;


public class Test {
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int findRepeatNumber(int[] nums) {
        int n = nums.length;
        //1~ n -1
        for (int num : nums)
            if (num < 0 || num >= n)
                return -1;
        //利用下标交换 保证下标 == 元素值
        for (int i = 0; i < n; i++) {
            while (nums[i] != i && nums[nums[i]] != nums[i])
                swap(nums, i, nums[i]);//交换nums[i]  nums[nums[i]]
            if (nums[i] != i)
                return nums[i];
        }
        return -1;
    }

//    public TreeNode sortedArrayToBST(int[] nums) {
//        if (nums == null || nums.length == 0) return null;
//        TreeNode root = null;
//        root = fun(root, nums, 0, nums.length - 1);
//        return root;
//    }
//
//
//    public TreeNode fun(TreeNode root, int[] nums, int i, int j) {
//        if (i == j) {
//            root = new TreeNode(nums[i]);
//            return root;
//        }
//        if (i > j) return null;
//        int mid = (i + j) / 2;
//        root = new TreeNode(nums[mid]);
//        root.left = fun(root.left, nums, i, mid - 1);
//        root.right = fun(root.right, nums, mid + 1, j);
//        return root;
//    }


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 2, 3, null, null, null, 4, null, null, null, null, null, null, 5};
        TreeUtils.TreeNode root = TreeUtils.createNormalTree(nums);
        String serializeStr = TreeUtils.serialize(root);
        System.out.println(serializeStr);
        System.out.println();
        TreeUtils.TreeNode rootTwo = TreeUtils.deserializeTwo(serializeStr);
        TreeUtils.levelTraverse(root);
        System.out.println();
        TreeUtils.levelTraverse(rootTwo);
    }
}
