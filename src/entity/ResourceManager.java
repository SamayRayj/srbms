package entity;
public class ResourceManager extends User {
    public ResourceManager(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String getRole() {
        return "ResourceManager";
    }
}