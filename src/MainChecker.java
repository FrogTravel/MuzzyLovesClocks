import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by ekaterina on 25.02.17.
 * Class for test purposed
 * I was too lazy to always change code in Main Main, so I've written another Main
 * It just make tests
 */
public class MainChecker {
    public static void main(String[] args) {
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("output.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        scanner.nextLine();
        printWriter.write(scanner.nextLine());
        printWriter.close();
    }
}
