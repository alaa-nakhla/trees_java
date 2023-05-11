package tree;

public class PrintTree {

    static private int arr[];
    static private boolean found[];
    static private int max;
    static private int num;

    static private int maxLevel(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(maxLevel(n.left), maxLevel(n.right));
    }

    static private void printSpace(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }

    static private void convert(Node n, int index) {
        if (index >= num) {
            return;
        }
        if (n == null) {
            return;
        }
        found[index] = true;
        arr[index] = n.value;
        convert(n.left, 2 * index);
        convert(n.right, 2 * index + 1);
    }

    public static void print(Node n) {
        max = maxLevel(n);
        num = (int) Math.pow(2, max);
        arr = new int[num];
        found = new boolean[num];
        for (int i = 1; i < num; i++) {
            found[i] = false;
        }
        convert(n, 1);
        for (int i = 0; i < max; i++) {
            int numNode = (int) Math.pow(2, i);
            int firstSpace = (int) Math.pow(2, max - i) - 1;
            printSpace(firstSpace);
            int between = (int) Math.pow(2, max - i + 1) - 1;
            for (int j = 0; j < numNode; j++) {
                int index = (int) Math.pow(2, i) + j;
                if (found[index]) {
                    System.out.print(arr[index]);
                } else {
                    System.out.print("�");
                }
                printSpace(between);
            }
            System.out.println("");
        }
    }
}
