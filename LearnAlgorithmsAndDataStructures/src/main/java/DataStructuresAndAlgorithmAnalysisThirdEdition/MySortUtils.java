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

    //-----------------------------插入排序--------------------------------
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] >= nums[j - 1]) {
                    return;
                }
                exchange(nums, j, j - 1);
            }
        }
    }

    //---------------------------希尔排序---------------------------------
    /**
     * 实际上用的是插入排序
     * @param nums
     */
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

    //---------------------------归并排序-----------------------------

    /**
     * 递归的归并排序
     * @param nums
     */
    public static void mergeSort_Recursion(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        int[] tempArray = new int[length];
        mergeSort_Recursion(nums, tempArray, 0, length - 1);
    }

    private static void mergeSort_Recursion(int[] nums, int[] tempArray, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = (low + high) / 2;
        mergeSort_Recursion(nums, tempArray, low, middle);
        mergeSort_Recursion(nums, tempArray, middle + 1, high);
        //最后一行才调用递归，任意时刻只有一个方法在使用临时数组,所以不会出现临时数组混乱的情况
        //当前一个有序数组的最后一个元素大于后一个有序数组时才需要排序
        if (nums[middle] > nums[middle + 1]){
            merge(nums, tempArray, low, middle, high);
        }
    }

    private static void merge(int[] nums, int[] tempArray, int low, int middle, int high) {
        int i = low, j = middle + 1;
        for (int k = low; k <= high; k++) {
            tempArray[k] = nums[k];
        }
        for (int k = low; k <= high; k++) {
            if (j > high || tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i++];
            } else {
                nums[k] = tempArray[j++];
            }
        }
    }


    /**
     * 非递归的归并排序
     * @param nums
     */
    public static void mergeSort_NonRecursion(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        int[] tempArray = new int[length];
        for (int sz = 1; sz < length; sz = sz + sz) {
            for (int lo = 0; lo < length - sz; lo += sz + sz) {
                merge(nums, tempArray, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, length - 1));
            }
        }
    }

    //---------------------------快速排序-----------------------------
    public static void quickSort(int[] nums) {

    }

    //---------------------------堆排序-----------------------------



    //------------------------外部排序---------------------------------
}
