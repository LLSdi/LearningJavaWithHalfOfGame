package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8NewFeature {
    public static void main(String[] args) {
        Integer[] integersOne = new Integer[]{1, 2, 3, 4, 5};
        ArrayList<Integer> listOne = new ArrayList() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        int[] intsOne = {1, 2, 3, 4, 5};


        // Integer[] 转 int[]
        int[] intsTwo = Arrays.stream(integersOne).mapToInt(Integer::valueOf).toArray();
        // ArrayList<Integer> 转 int[]
        int[] intsThree = listOne.stream().mapToInt(Integer::valueOf).toArray();


        // int[] 转 Integer[]
        Integer[] integersTwo = (Integer[]) Arrays.stream(intsThree).mapToObj(Integer::valueOf).toArray();
        // ArrayList<Integer> 转 Integer[]
        Integer[] integersThree = (Integer[]) listOne.toArray();

        // int[] 转 ArrayList<Integer>
        List<Integer> listTwo = Arrays.stream(intsOne).boxed().collect(Collectors.toList());
        //Integer[] 转 ArrayList<Integer>
        List<Integer> listThree = Arrays.asList(integersOne);
    }
}
