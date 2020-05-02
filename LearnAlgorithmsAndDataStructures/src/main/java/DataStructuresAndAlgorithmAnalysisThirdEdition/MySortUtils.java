package DataStructuresAndAlgorithmAnalysisThirdEdition;

import java.util.Random;

/**
 * @author halfOfGame
 * @create 2020-05-02,8:53
 */
public class MySortUtils {

    //---------------------------定义数组---------------------------------

    /**
     * 正序数组
     */
    public static int[] getPositiveOrderArray(int length) {
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }


    /**
     * 倒序数组
     */
    public static int[] getReverseOrderArray(int length) {
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = length - i;
        }
        return nums;
    }

    /**
     * 随机数组
     * 取得长度为length，值在[1, maxvalue]的数组
     */
    public static int[] getRandomOrderArray(int length, int maxValue) {
        Random random = new Random();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(maxValue) + 1;
        }
        return nums;
    }


    //-------------------------工具方法----------------------------------

    /**
     * 交换两个元素
     */
    public static void exchange(int[] nums, int i, int j) {
        int tempNum = nums[i];
        nums[i] = nums[j];
        nums[j] = tempNum;
    }

    /**
     * 遍历数组
     */
    public static void display(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    /**
     * 检查数组是否有序
     */
    public static boolean isSorted(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    //-------------------------选择排序----------------------------------
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length, maxNumIndex = 0, i, j;
        for (i = 0; i < length - 1; i++) {
            for (j = 1; j < length - i; j++) {
                if (nums[j] > nums[maxNumIndex]) {
                    maxNumIndex = j;
                }
            }
            exchange(nums, maxNumIndex, j - 1);
            maxNumIndex = 0;
        }
    }

    //---------------------------冒泡排序---------------------------------------
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    exchange(nums, j, j + 1);
                }
            }
        }
    }

    //---------------------------希尔排序---------------------------------
    public static void shellSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (nums[j] >= nums[j - h]) {
                        break;
                    }
                    exchange(nums, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void myShellSort(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        int length = nums.length;
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1){

        }
    }

    //------------------------外部排序---------------------------------
}
