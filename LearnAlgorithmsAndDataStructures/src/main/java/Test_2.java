import DataStructuresAndAlgorithmAnalysisThirdEdition.MyPriorityQueue;
import DataStructuresAndAlgorithmAnalysisThirdEdition.MySearchUtils;
import DataStructuresAndAlgorithmAnalysisThirdEdition.MySortUtils;

/**
 * @author halfOfGame
 * @create 2020-05-04,8:26
 */
public class Test_2 {
    public static void main(String[] args) {
        int[] nums = MySortUtils.getPositiveOrderArray(10);
        MySortUtils.display(nums);
        int length = nums.length;
        for (int i = 1; i <= length; i++) {
            System.out.print(i + ":" + MySearchUtils.binarySearch(nums, i) + " ");
        }
    }
}
