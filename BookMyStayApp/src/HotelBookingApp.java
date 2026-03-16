import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {
    private Queue<Reservation> requestQueue;
    private InventoryService inventoryService;
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private int idCounter = 1;

    BookingService(Queue<Reservation> requestQueue, InventoryService inventoryService) {
        this.requestQueue = requestQueue;
        this.inventoryService = inventoryService;
    }

    void processBookings() {
        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.poll();
            String type = r.roomType;

            if (inventoryService.getAvailability(type) > 0) {
                String roomId = type.substring(0,1).toUpperCase() + idCounter++;
                allocatedRooms.putIfAbsent(type, new HashSet<>());

                if (!allocatedRooms.get(type).contains(roomId)) {
                    allocatedRooms.get(type).add(roomId);
                    inventoryService.decrement(type);
                    System.out.println("Reservation Confirmed for " + r.guestName + " | Room ID: " + roomId + " | Type: " + type);
                }
            } else {
                System.out.println("No rooms available for " + r.guestName + " | Type: " + type);
            }
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        Queue<Reservation> requestQueue = new LinkedList<>();

        requestQueue.add(new Reservation("Alice", "Single"));
        requestQueue.add(new Reservation("Bob", "Double"));
        requestQueue.add(new Reservation("Charlie", "Single"));

        InventoryService inventory = new InventoryService();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        BookingService bookingService = new BookingService(requestQueue, inventory);
        bookingService.processBookings();
    }
}