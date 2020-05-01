/**
 * @author halfOfGame
 * @create 2020-04-03,8:06
 */
public class BTest {

    public static void bubbleSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++){
                if (nums[j] > nums[j + 1]){
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }


    public static void Test() {

    }

    public static void main(String[] args) {
        int[] ints = {5, 4, 3, 2, 1};
        bubbleSort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
