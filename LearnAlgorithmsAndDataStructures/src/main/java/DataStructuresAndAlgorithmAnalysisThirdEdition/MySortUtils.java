package DataStructuresAndAlgorithmAnalysisThirdEdition;

/**
 * @author halfOfGame
 * @create 2020-05-02,8:53
 */
public class MySortUtils {

    //----------------------------遍历数组---------------------------------
    public static void display(int[] nums){
        for(int num : nums){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    //-------------------------工具方法----------------------------------

    /**
     * 交换两个元素
     */
    public static void exchange(int[] nums, int i, int j){
        int tempNum = nums[i];
        nums[i] = nums[j];
        nums[j] = tempNum;
    }

    //-------------------------选择排序----------------------------------
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int length = nums.length, maxNumIndex = 0, i, j;
        for (i = 0; i < length - 1; i++) {
            for (j = 1; j < length - i; j++) {
                if (nums[j] > nums[maxNumIndex])
                    maxNumIndex = j;
            }
            exchange(nums, maxNumIndex, j - 1);
            maxNumIndex = 0;
        }
    }

    //---------------------------冒泡排序---------------------------------------
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1])
                    exchange(nums, j, j + 1);
            }
        }
    }

    //---------------------------希尔排序---------------------------------
    public static void shellSort(int[] nums) {

    }
}
