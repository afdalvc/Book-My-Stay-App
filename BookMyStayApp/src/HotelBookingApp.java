import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    void addRoomType(String type, int count) {
        rooms.put(type, count);
    }

    void bookRoom(String type) throws InvalidBookingException {
        if (!rooms.containsKey(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }

        int available = rooms.get(type);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + type);
        }

        rooms.put(type, available - 1);
        System.out.println("Room booked successfully for type: " + type);
    }
}

class BookingValidator {
    static void validateRoomType(String type) throws InvalidBookingException {
        if (type == null || type.isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        String[] requests = {"Single", "Suite", "Double", "Double"};

        for (String type : requests) {
            try {
                BookingValidator.validateRoomType(type);
                inventory.bookRoom(type);
            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }
}