import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    void addRoomType(String type, int count) {
        rooms.put(type, count);
    }

    synchronized boolean allocateRoom(String type) {
        int available = rooms.getOrDefault(type, 0);
        if (available > 0) {
            rooms.put(type, available - 1);
            return true;
        }
        return false;
    }

    int getAvailability(String type) {
        return rooms.getOrDefault(type, 0);
    }
}

class BookingProcessor implements Runnable {
    private Queue<Reservation> queue;
    private Inventory inventory;

    BookingProcessor(Queue<Reservation> queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation r;
            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                r = queue.poll();
            }

            if (inventory.allocateRoom(r.roomType)) {
                System.out.println(Thread.currentThread().getName() + " confirmed booking for " + r.guestName + " (" + r.roomType + ")");
            } else {
                System.out.println(Thread.currentThread().getName() + " failed booking for " + r.guestName + " (" + r.roomType + ")");
            }
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Single"));
        bookingQueue.add(new Reservation("Charlie", "Single"));
        bookingQueue.add(new Reservation("David", "Double"));
        bookingQueue.add(new Reservation("Eva", "Double"));

        Inventory inventory = new Inventory();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        Thread t1 = new Thread(new BookingProcessor(bookingQueue, inventory), "Processor-1");
        Thread t2 = new Thread(new BookingProcessor(bookingQueue, inventory), "Processor-2");

        t1.start();
        t2.start();
    }
}