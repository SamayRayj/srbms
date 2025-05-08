package entity;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ResourceSelection> selections = new ArrayList<>();

    public void add(ResourceSelection selection) {
        selections.add(selection);
    }

    public List<ResourceSelection> getSelections() {
        return selections;
    }

    public void clear() {
        selections.clear();
    }

    public double getTotalCost() {
        return selections.stream().mapToDouble(ResourceSelection::getCost).sum();
    }

    public boolean isEmpty() {
        return selections.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart:\n");
        for (ResourceSelection rs : selections) {
            sb.append("- ").append(rs).append("\n");
        }
        sb.append("Total: ₹").append(getTotalCost());
        return sb.toString();
    }
}
