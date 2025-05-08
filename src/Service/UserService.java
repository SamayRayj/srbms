package Service;

import java.util.*;
import entity.*;
import utils.ConsoleUI;

public class UserService {
    private Map<String, User> usersByEmail = new HashMap<>();

    public User registerUser(String name, String email, String password, String role) {
        if (getUserByEmail(email) != null) {
            ConsoleUI.error("Email already registered. Please login instead.");
            return null;
        }

        User user;
        switch (role.toLowerCase()) {
            case "admin" -> user = new Admin(name, email, password);
            case "resourcemanager" -> user = new ResourceManager(name, email, password);
            default -> user = new RegularUser(name, email, password);
        }

        usersByEmail.put(email.toLowerCase(), user); // 🔁 Store using lowercase for consistency
        return user;
    }

    public User getUserByEmail(String email) {
        return usersByEmail.get(email.toLowerCase());
    }

    public User login(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(usersByEmail.values());
    }
    public boolean updateUserProfile(User user, String newName, String newEmail) {
        if (!user.getEmail().equalsIgnoreCase(newEmail) && getUserByEmail(newEmail) != null) {
            ConsoleUI.error("Email already in use by another account.");
            return false;
        }

        usersByEmail.remove(user.getEmail().toLowerCase()); // remove old email
        user.setName(newName);
        user.setEmail(newEmail);
        usersByEmail.put(newEmail.toLowerCase(), user); // re-add with new email
        ConsoleUI.success("Profile updated successfully.");
        return true;
    }
    public boolean changeUserPassword(User user, String oldPassword, String newPassword) {
        if (!user.checkPassword(oldPassword)) {
            ConsoleUI.error("Old password is incorrect.");
            return false;
        }

        if (newPassword.length() < 6) {
            ConsoleUI.error("New password must be at least 6 characters.");
            return false;
        }

        user.setPassword(newPassword);
        ConsoleUI.success("Password updated successfully.");
        return true;
    }
}