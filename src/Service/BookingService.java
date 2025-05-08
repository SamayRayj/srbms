package Service;
import entity.*;
import java.time.LocalDateTime;
import java.util.*;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();

    public boolean isResourceAvailable(Resource resource, DateTimeRange range) {
        for (Booking b : bookings) {
            if (b.getResource().getId().equals(resource.getId()) &&
                    b.getTimeRange().overlapsWith(range)) {
                return false;
            }
        }
        return true;
    }

    public Booking makeBooking(User user, Resource resource, DateTimeRange range) {
        if (!isResourceAvailable(resource, range)) return null;

        Booking booking = new Booking(user, resource, range);
        bookings.add(booking);
        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public List<Booking> getBookingsForUser(User user) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getUser().getId().equals(user.getId())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Booking> getBookingsForResource(Resource resource) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getResource().getId().equals(resource.getId())) {
                result.add(b);
            }
        }
        return result;
    }
}