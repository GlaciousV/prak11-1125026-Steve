package util;

import java.util.Scanner;

public class ScannerUtil {

    public static String inputString(String text, Scanner scanner) {
        System.out.print(text);
        String input = scanner.nextLine();
        return input;
    }

    public static double inputDouble(String text, Scanner scanner) {
        System.out.print(text);
        double input = scanner.nextDouble();
        scanner.nextLine(); 
        return input;
    }

    public static int inputInt(String text, Scanner scanner) {
        System.out.print(text);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}