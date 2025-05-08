package entity;
import java.util.UUID;
public abstract class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public abstract String getRole();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, email, getRole());
    }

    public String getId() {
        return email;
    }
}
