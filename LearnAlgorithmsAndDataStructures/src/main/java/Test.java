import DataStructuresAndAlgorithmAnalysisThirdEdition.MySortUtils;
import DataStructuresAndAlgorithmAnalysisThirdEdition.MyTree;
import edu.princeton.cs.algs4.Stopwatch;

public class Test {
    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        int[] nums = {10, 9, 8, 7, 5, 6, 4, 3, 2, 1};
        System.out.println("排序前：");
        MySortUtils.display(nums);
        System.out.println("排序后：");
        MySortUtils.bubbleSort(nums);
        MySortUtils.display(nums);
        System.out.println(timer.elapsedTime());
    }
}
