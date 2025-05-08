package entity;
public class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}