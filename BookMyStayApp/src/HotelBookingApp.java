abstract class Room {

    private String roomType;
    private int beds;
    private double size;
    private double price;

    public Room(String roomType, int beds, double size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price: $" + price);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 80);
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 120);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 250);
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 5;
        int suiteRoomAvailable = 2;

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("===== BOOK MY STAY APP =====");
        System.out.println("Available Room Types:\n");

        single.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable);
    }
}