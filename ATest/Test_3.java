import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author halfOfGame
 * @create 2020-04-30,16:13
 */
public class Test_3 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            arrayList.add(10 - i);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(arrayList);
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

    }
}
