import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;

    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rental> getRentals() {
        return Collections.unmodifiableList(rentals);
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void clearRentals() {
        rentals.clear();
    }
}