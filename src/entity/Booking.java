package entity;
public class Booking {
    private User user;
    private Resource resource;
    private DateTimeRange timeRange;
    private double totalCost;

    public Booking(User user, Resource resource, DateTimeRange timeRange) {
        this.user = user;
        this.resource = resource;
        this.timeRange = timeRange;
        this.totalCost = timeRange.getDurationInHours() * resource.getCostPerHour();
    }

    public User getUser() { return user; }
    public Resource getResource() { return resource; }
    public DateTimeRange getTimeRange() { return timeRange; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return "Booking by " + user.getName() + " for " + resource.getName() +
                " | " + timeRange + " | ₹" + totalCost;
    }
}
