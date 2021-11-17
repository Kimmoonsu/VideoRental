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

	public String getReport() {
		StringBuffer result = new StringBuffer();
		result.append("Customer Report for " + getName() + "\n");

		List<Rental> rentals = getRentals();
		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental rental : rentals) {
			double rentalCharge = rental.getRentalCharge();
			int rentalPoint = rental.getRentalPoint();

			result.append("\t" + rental.getTitle() + "\tDays rented: " + rental.getDaysRented() + "\tCharge: " + rentalCharge
					+ "\tPoint: " + rentalPoint + "\n");

			totalCharge += rentalCharge;
			totalPoint += rentalPoint ;
		}

		result.append("Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n");


		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result.toString();
	}

	public void clearRentals() {
		rentals.clear();
	}
}
