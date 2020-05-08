import DataStructuresAndAlgorithmAnalysisThirdEdition.MySortUtils;


public class Test {
    public static void main(String[] args) {
        int[] nums = null;
        for (int i = 1; i <= 100; i++) {
            nums = MySortUtils.getRandomOrderArrayWithRepetionNumber(10, 5);
            System.out.println("第" + i + "次排序前：");
            MySortUtils.display(nums);
            MySortUtils.heapSort(nums);
            assert MySortUtils.isSorted(nums);
            System.out.println("第" + i + "次排序后：");
            MySortUtils.display(nums);
            System.out.println();
        }
    }
}
