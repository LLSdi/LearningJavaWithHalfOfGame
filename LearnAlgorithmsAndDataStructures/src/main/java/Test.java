import DataStructuresAndAlgorithmAnalysisThirdEdition.MySortUtils;


public class Test {
    public static void main(String[] args) {
        int[] nums = MySortUtils.getReverseOrderArray(10);
        System.out.println("排序前：");
        MySortUtils.display(nums);
        MySortUtils.mergeSort_NonRecursion(nums);
        System.out.println("排序后：");
        MySortUtils.display(nums);
    }
}
