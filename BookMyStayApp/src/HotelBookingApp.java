import java.util.*;

class Room {
    String type;
    double price;
    String amenities;

    Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    void display() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Amenities: " + amenities);
        System.out.println();
    }
}

class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    void addRoom(String type, int count) {
        availability.put(type, count);
    }

    int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    Set<String> getRoomTypes() {
        return availability.keySet();
    }
}

class SearchService {
    Inventory inventory;
    Map<String, Room> rooms;

    SearchService(Inventory inventory, Map<String, Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    void searchRooms() {
        for (String type : inventory.getRoomTypes()) {
            int available = inventory.getAvailability(type);
            if (available > 0) {
                Room r = rooms.get(type);
                r.display();
                System.out.println("Available: " + available);
                System.out.println("----------------------");
            }
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addRoom("Single", 3);
        inventory.addRoom("Double", 0);
        inventory.addRoom("Suite", 2);

        Map<String, Room> rooms = new HashMap<>();

        rooms.put("Single", new Room("Single", 2000, "WiFi, TV"));
        rooms.put("Double", new Room("Double", 3500, "WiFi, TV, AC"));
        rooms.put("Suite", new Room("Suite", 6000, "WiFi, TV, AC, Mini Bar"));

        SearchService service = new SearchService(inventory, rooms);

        System.out.println("Available Rooms:");
        service.searchRooms();
    }
}