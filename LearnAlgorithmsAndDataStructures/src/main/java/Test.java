import DataStructuresAndAlgorithmAnalysisThirdEdition.MySortUtils;
import DataStructuresAndAlgorithmAnalysisThirdEdition.MyTree;
import edu.princeton.cs.algs4.Stopwatch;

public class Test {
    public static void main(String[] args) {

        int[] nums = MySortUtils.getReverseOrderArray(10);
        System.out.println("排序前：");
        MySortUtils.display(nums);
        MySortUtils.shellSort(nums);
        System.out.println("排序后：");
        MySortUtils.display(nums);
    }
}
