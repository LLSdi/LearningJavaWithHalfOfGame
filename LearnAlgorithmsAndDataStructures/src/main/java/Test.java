import DataStructuresAndAlgorithmAnalysisThirdEdition.MyTree;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        myTree.createTree(new Integer[]{1, 2, 3, 4, 5, null, null, null, null});
        myTree.levelTraverseTres(myTree.getHead());
    }
}
