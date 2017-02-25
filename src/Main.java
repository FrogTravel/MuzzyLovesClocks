import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int maxValue = 0;
        int maxCost = 0;

        String[] allValues = scanner.nextLine().split(" ");
        int totalWeight = getWeight(scanner.nextLine());
        int[] values = new int[allValues.length / 2];
        int[] weights = new int[allValues.length / 2];

        int index = 0;
        for (int i = 0; i < allValues.length; i += 2) {
            values[index] = getValue(allValues[i]);
            weights[index] = getWeight(allValues[i + 1]);

            System.out.println("Value: " + values[index] + "\nWeight: " + weights[index]);
            index++;
        }


        int[][] table = new int[totalWeight + 1][values.length + 1];

        for (int i = 1; i <= totalWeight; i++) {
            for (int j = 1; j <= values.length; j++) {
                int number = 0;
                if ((i - weights[j-1] >= 0)&&(j-1 >= 0)) {
                    number = table[i - weights[j-1]][j - 1];
                }
                table[i][j] = Math.max(table[i][j-1], values[j - 1] + number);

                  // showTable(table);

//                try{
//                    table[i][j] = Math.max(table[i][j-1], values[j] + table[i-weights[j]][j-1]);
//                }catch (ArrayIndexOutOfBoundsException e){
//
//                }
//                int first = 0;
//                int second = 0;
//                if(j-1 >= 0){
//                    first = table[i][j-1];
//                    if(i-weights[j] >= 0)
//                        second = table[i-weights[j]][j-1];
//                }
//
//                table[i][j] = Math.max(first, values[j] + second);
//
//
//                //showTable(table);
            }
        }


        showTable(table);

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("output.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        printWriter.print(table[totalWeight][values.length]);
        printWriter.close();
    }

    private static void showTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("----------------------------------");
    }

    private static int getValue(String s) {
        String[] tokens = s.split("[:]");
        return Integer.valueOf(tokens[0]) * 60 + Integer.valueOf(tokens[1]);
    }

    private static int getWeight(String s) {
        double number = Double.valueOf(s) * 100;
        return (int) number;
    }
}
