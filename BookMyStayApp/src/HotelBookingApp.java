import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    void addService(String reservationId, Service service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    double calculateTotalCost(String reservationId) {
        double total = 0;
        List<Service> services = reservationServices.get(reservationId);
        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }
        return total;
    }

    void displayServices(String reservationId) {
        List<Service> services = reservationServices.get(reservationId);
        if (services != null) {
            for (Service s : services) {
                System.out.println(s.name + " - " + s.cost);
            }
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        Service breakfast = new Service("Breakfast", 500);
        Service airportPickup = new Service("Airport Pickup", 1200);
        Service spa = new Service("Spa", 2000);

        String reservationId = "R101";

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        System.out.println("Services for Reservation: " + reservationId);
        manager.displayServices(reservationId);

        double total = manager.calculateTotalCost(reservationId);
        System.out.println("Total Add-On Cost: " + total);
    }
}