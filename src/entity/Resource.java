package entity;
import java.util.UUID;
public class Resource {
    private String id;
    private String name;
    private String type;
    private double costPerHour;private static int idCounter = 0;

    private static synchronized int generateId() {
        return ++idCounter;
    }

    public Resource(String name, String type, double costPerHour) {
        this.id = String.valueOf(generateId());
        this.name = name;
        this.type = type;
        this.costPerHour = costPerHour;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getCostPerHour() { return costPerHour; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setCostPerHour(double costPerHour) { this.costPerHour = costPerHour; }

    @Override
    public String toString() {
        return id + " " + name + " [" + type + "] - ₹" + costPerHour + "/hr";
    }
}