import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] allValues = scanner.nextLine().split(" ");//Read line of data
        int totalWeight = getWeight(scanner.nextLine());
        int[] values = new int[allValues.length / 2];//half of first line are values
        int[] weights = new int[allValues.length / 2];//half of second line are weight

        //Parse data
        int index = 0;
        for (int i = 0; i < allValues.length; i += 2) {
            values[index] = getValue(allValues[i]);
            weights[index] = getWeight(allValues[i + 1]);

            index++;
        }

        writeFile(findOptimal(totalWeight, values, weights));
    }

    /**
     * Transform clock format to minutes (so multiple to 60)
     * @param s data
     * @return transformed data
     */
    private static int getValue(String s) {
        String[] tokens = s.split("[:]");
        return Integer.valueOf(tokens[0]) * 60 + Integer.valueOf(tokens[1]);
    }

    /**
     * Multiple Muzzy's money to 100 so it is more convenient to use
     * @param s data
     * @return transformed data
     */
    private static int getWeight(String s) {
        double number = Double.valueOf(s) * 100;
        return (int) number;
    }

    /**
     * Write answer to "output.txt"
     * @param element to write
     */
    private static void writeFile(int element){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("output.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        printWriter.print(element);
        printWriter.close();
    }

    /**
     * Classic 1/0 knapsack problem. Find maximum value that we can take with current maximum cost(weight)
     * @param totalWeight maximum weight
     * @param values of elements
     * @param weights of elements
     * @return right bottom element
     */
    private static int findOptimal(int totalWeight, int[] values, int[] weights){
        int[][] table = new int[totalWeight + 1][values.length + 1];

        for (int j = 1; j <= values.length; j++) {
            for (int i = 1; i <= totalWeight; i++) {
                if(weights[j-1] > i){
                    table[i][j] = table[i][j-1];
                }else {
                    int number = 0;
                    if (i - weights[j - 1] >= 0) {
                        number = table[i - weights[j - 1]][j - 1];
                    }
                    table[i][j] = Math.max(table[i][j - 1], values[j - 1] + number);
                }
            }
        }
        return table[totalWeight][values.length];
    }
}
