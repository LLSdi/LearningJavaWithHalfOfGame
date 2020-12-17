import DataStructuresAndAlgorithmAnalysisThirdEdition.MyTree;

public class MyTreeTest {
    public static void main(String[] args) {
        Integer[] nums = {44,18,10,22,1,38,2,40,null,41,9,7,null,14,21,null,34,30,27,3,39,23,35,6,null,17,25,36,null,null,19,16,null,null,12,20,11,null,8,null,28,24,null,29,13,null,37,31,null,null,null,32,null,null,null,4,null,null,null,42,null,null,null,15,43,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,5,33,null,26};
        MyTree tree = new MyTree(nums);
        tree.postTraverseTree_Iteration(tree.head);
        System.out.println();
        tree.postTraverseTree_Recursion(tree.head);
    }
}
