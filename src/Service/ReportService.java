package Service;
import java.util.*;
import entity.*;
import java.util.*;

public class ReportService {
    private BookingService bookingService;

    public ReportService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void printUserBookingReport(User user) {
        System.out.println(" Booking Report for " + user.getName() + ":");
        List<Booking> bookings = bookingService.getBookingsForUser(user);
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        for (Booking b : bookings) {
            System.out.println("→ " + b);
        }
    }

    public void printResourceUsageReport(Resource resource) {
        System.out.println(" Usage Report for " + resource.getName() + ":");
        List<Booking> bookings = bookingService.getBookingsForResource(resource);
        if (bookings.isEmpty()) {
            System.out.println("No bookings.");
            return;
        }

        double total = 0;
        for (Booking b : bookings) {
            System.out.println("→ " + b);
            total += b.getTotalCost();
        }
        System.out.println("Total Revenue: ₹" + total);
    }

    public void printAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println(" No bookings made yet.");
            return;
        }

        System.out.println(" All Bookings:");
        for (Booking b : bookings) {
            System.out.println("→ " + b);
        }
    }
}
