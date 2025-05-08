package utils;
import entity.*;
import Service.*;

import java.time.LocalDateTime;
import java.util.*;

public class DataSeeder {
    public static void seedData(UserService userService, ResourceService resourceService, BookingService bookingService) {
        User admin = userService.registerUser("Rayush", "rayushjain8099@gmail.com", "123456", "admin");
        User manager = userService.registerUser("Tushar", "Tushar@gmail.com", "123456", "resourcemanager");
        User user1 = userService.registerUser("A", "A@gmail.com", "123456", "regularuser");

        Resource r1 = resourceService.addResource("Conference Room", "Room", 500.0);
        Resource r2 = resourceService.addResource("Projector", "Equipment", 150.0);

        LocalDateTime now = LocalDateTime.now();
        DateTimeRange range1 = new DateTimeRange(now.plusHours(2), now.plusHours(5));
        DateTimeRange range2 = new DateTimeRange(now.plusDays(1), now.plusDays(1).plusHours(3));

        bookingService.makeBooking(user1, r1, range1);
        bookingService.makeBooking(user1, r2, range2);
    }
}
