
import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        int[] input = {3, 7, 1, 2, 6, 4};
        int result = findMissingNumber(input);
        if (result != 0) {
            System.out.println("The missing number is " + result);
        } else {
            System.out.println("There is no missing number");
        }

    }

    public static int findMissingNumber(int[] input) {
        Arrays.sort(input);
        for (int i = 0; i < input.length; i++) {
            if (input[i] != i+1) {
                return i+1;
            }
        }
        return 0;
    }
}
