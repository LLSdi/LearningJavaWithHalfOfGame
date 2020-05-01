import DataStructuresAndAlgorithmAnalysisThirdEdition.MyTree;

public class Test {
    public static void main(String[] args) {
        MyTree myTree = new MyTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.print("递归：");
        myTree.postTraverseTree_Recursion(myTree.head);
        System.out.println();
        System.out.print("迭代：");
        myTree.postTraverseTree_Iteration(myTree.head);
    }
}
