import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0 ;
			int daysRented = 0;

			if (each.getStatus() == 1) { // returned Video
				long diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
				daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
			} else { // not yet returned
				long diff = new Date().getTime() - each.getRentDate().getTime();
				daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
			}

			switch (each.getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			}

			eachPoint++;

			if ((each.getPriceCode() == Video.NEW_RELEASE) )
				eachPoint++;

			if ( daysRented > each.getDaysRentedLimit() )
				eachPoint -= Math.min(eachPoint, each.getLateReturnPointPenalty()) ;

			result += "\t" + each.getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	public void clearRentals() {
		rentals.clear();
	}
}
