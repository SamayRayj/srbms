package utils;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern emailPattern =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public static boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String pwd) {
        return pwd.length() >= 6;
    }

    public static boolean isPositiveDouble(String value) {
        try {
            return Double.parseDouble(value) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
