public class AssertTest {
    public static void main(String[] args) {
        boolean isSafe = true;
        assert isSafe : "Not safe at all";
        System.out.println("断言通过!");
    }
}
