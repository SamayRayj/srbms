package utils;
import java.util.Scanner;

public class ConsoleUI {
    private static final Scanner sc = new Scanner(System.in);

    public static String prompt(String msg) {
        System.out.print(msg + ": ");
        return sc.nextLine().trim();
    }

    public static int promptInt(String msg) {
        while (true) {
            try {
                System.out.print(msg + ": ");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
            }
        }
    }

    public static double promptDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg + ": ");
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a valid amount.");
            }
        }
    }

    public static void info(String msg) {
        System.out.println("info:  " + msg);
    }

    public static void success(String msg) {
        System.out.println("Success!!! " + msg);
    }

    public static void error(String msg) {
        System.out.println("ERRORRRRRRR " + msg);
    }
}
