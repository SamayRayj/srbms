package srbms

import entity.*;
import Service.*;
import utils.*;

import java.time.LocalDateTime;

public class SRBMSApp {
    private static final UserService userService = new UserService();
    private static final ResourceService resourceService = new ResourceService();
    private static final BookingService bookingService = new BookingService();
    private static final ReportService reportService = new ReportService(bookingService);

    public static void main(String[] args) {
        DataSeeder.seedData(userService, resourceService, bookingService);
        ConsoleUI.info("Welcome to Smart Resource Booking & Management System (SRBMS)");

        while (true) {
            String email = ConsoleUI.prompt("📧 Enter email (or 'exit' to quit)");
            if (email.equalsIgnoreCase("exit")) break;

            String password = ConsoleUI.prompt("🔑 Enter password");
            User user = userService.login(email, password);

            if (user != null) {
                ConsoleUI.success("Welcome, " + user.getName());
                if (user instanceof Admin) adminMenu((Admin) user);
                else if (user instanceof ResourceManager) managerMenu((ResourceManager) user);
                else userMenu(user);
            } else {
                ConsoleUI.error("Invalid login. Try again.");
            }
        }
    }

    // 🧑‍💼 ADMIN MENU
    private static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View All Users");
            System.out.println("2. View All Bookings");
            System.out.println("3. Logout");

            int choice = ConsoleUI.promptInt("Choose an option");

            switch (choice) {
                case 1 -> userService.getAllUsers().forEach(System.out::println);
                case 2 -> reportService.printAllBookings();
                case 3 -> { return; }
                default -> ConsoleUI.error("Invalid choice.");
            }
        }
    }

    // 📦 MANAGER MENU
    private static void managerMenu(ResourceManager manager) {
        while (true) {
            System.out.println("\n--- RESOURCE MANAGER MENU ---");
            System.out.println("1. Add Resource");
            System.out.println("2. View All Resources");
            System.out.println("3. Update Resource");
            System.out.println("4. Delete Resource");
            System.out.println("5. Resource Usage Report");
            System.out.println("6. Logout");

            int choice = ConsoleUI.promptInt("Choose an option");

            switch (choice) {
                case 1 -> {
                    String name = ConsoleUI.prompt("Enter resource name");
                    String type = ConsoleUI.prompt("Enter resource type");
                    double cost = ConsoleUI.promptDouble("Enter cost per hour");
                    resourceService.addResource(name, type, cost);
                    ConsoleUI.success("Resource added.");
                }
                case 2 -> resourceService.getAllResources().forEach(System.out::println);
                case 3 -> {
                    String id = ConsoleUI.prompt("Enter resource ID");
                    Resource res = resourceService.getResourceById(id);
                    if (res != null) {
                        String newName = ConsoleUI.prompt("New name");
                        String newType = ConsoleUI.prompt("New type");
                        double newCost = ConsoleUI.promptDouble("New cost/hour");
                        resourceService.updateResource(res, newName, newType, newCost);
                        ConsoleUI.success("Resource updated.");
                    } else ConsoleUI.error("Resource not found.");
                }
                case 4 -> {
                    String id = ConsoleUI.prompt("Enter resource ID to delete");
                    resourceService.deleteResource(id);
                    ConsoleUI.success("Resource deleted.");
                }
                case 5 -> {
                    String id = ConsoleUI.prompt("Enter resource ID for report");
                    Resource res = resourceService.getResourceById(id);
                    if (res != null) reportService.printResourceUsageReport(res);
                    else ConsoleUI.error("Resource not found.");
                }
                case 6 -> { return; }
                default -> ConsoleUI.error("Invalid choice.");
            }
        }
    }

    // 👤 USER MENU
    private static void userMenu(User user) {
        while (true) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. View Available Resources");
            System.out.println("2. Make a Booking");
            System.out.println("3. View My Bookings");
            System.out.println("4. Booking Report");
            System.out.println("5. Logout");

            int choice = ConsoleUI.promptInt("Choose an option");

            switch (choice) {
                case 1 -> resourceService.getAllResources().forEach(System.out::println);
                case 2 -> {
                    String id = ConsoleUI.prompt("Enter resource ID to book");
                    Resource res = resourceService.getResourceById(id);
                    if (res == null) {
                        ConsoleUI.error("Resource not found.");
                        break;
                    }
                    try {
                        LocalDateTime start = LocalDateTime.parse(ConsoleUI.prompt("Start Time (yyyy-MM-ddTHH:mm)"));
                        LocalDateTime end = LocalDateTime.parse(ConsoleUI.prompt("End Time (yyyy-MM-ddTHH:mm)"));
                        DateTimeRange range = new DateTimeRange(start, end);

                        if (!bookingService.isResourceAvailable(res, range)) {
                            ConsoleUI.error("Resource is already booked during that time.");
                            break;
                        }

                        Booking booking = bookingService.makeBooking(user, res, range);
                        if (booking != null) {
                            ConsoleUI.success("Booking successful!");
                            System.out.println(booking);
                        } else ConsoleUI.error("Booking failed.");
                    } catch (Exception e) {
                        ConsoleUI.error("Invalid date/time format.");
                    }
                }
                case 3 -> bookingService.getBookingsForUser(user).forEach(System.out::println);
                case 4 -> reportService.printUserBookingReport(user);
                case 5 -> { return; }
                default -> ConsoleUI.error("Invalid choice.");
            }
        }
    }
}
