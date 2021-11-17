import java.util.List;

public class CustomerReporter {

    public static final int ONE_FREE_COUPON_POINT = 10;
    public static final int TWO_FREE_COUPON_POINT = 30;

    public static String getReport(Customer customer) {
        StringBuffer result = new StringBuffer();
        result.append("Customer Report for " + customer.getName() + "\n");

        List<Rental> rentals = customer.getRentals();
        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental rental : rentals) {
            double rentalCharge = rental.getRentalCharge();
            int rentalPoint = rental.getRentalPoint();

            result.append("\t" + rental.getTitle() + "\tDays rented: " + rental.getDaysRented() + "\tCharge: " + rentalCharge
                    + "\tPoint: " + rentalPoint + "\n");

            totalCharge += rentalCharge;
            totalPoint += rentalPoint;
        }

        result.append("Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n");


        if (totalPoint >= ONE_FREE_COUPON_POINT) {
            System.out.println("Congrat! You earned one free coupon");
        }
        if (totalPoint >= TWO_FREE_COUPON_POINT) {
            System.out.println("Congrat! You earned two free coupon");
        }
        return result.toString();
    }
}