import DataStructuresAndAlgorithmAnalysisThirdEdition.MyTree;

public class Test {
    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        myTree.createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(myTree.getData(myTree.lowestCommonAncestor(myTree.getHead(), myTree.getLeft(myTree.getHead()), myTree.getRight(myTree.getHead()))));
    }
}
