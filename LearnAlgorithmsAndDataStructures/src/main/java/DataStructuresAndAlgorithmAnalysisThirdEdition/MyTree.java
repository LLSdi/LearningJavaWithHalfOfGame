package DataStructuresAndAlgorithmAnalysisThirdEdition;

public class MyTree<AnyType> {
    private static class TreeNode<AnyType> {
        AnyType data;
        TreeNode left;
        TreeNode right;

        public TreeNode(AnyType data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


}
