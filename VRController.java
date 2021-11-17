import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class VRController {
    private List<Customer> customers = new ArrayList<Customer>();
    private List<Video> videos = new ArrayList<Video>();

    public VRController() {
    }

    List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    List<Video> getVideos() {
        return Collections.unmodifiableList(videos);
    }

    void clearRentals(String customerName) {
        Customer foundCustomer = findCustomer(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
            return;
        }

        System.out.println("Name: " + foundCustomer.getName() +
                "\tRentals: " + foundCustomer.getRentals().size());
        for (Rental rental : foundCustomer.getRentals()) {
            System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
            System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
        }

        foundCustomer.clearRentals();
    }

    Customer findCustomer(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }

    void returnVideo(String customerName, String videoTitle) {
        Customer foundCustomer = findCustomer(customerName);

        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.hasVideo(videoTitle)
                    && rental.isRented()) {
                rental.returnVideo();
                break;
            }
        }
    }

    void getCustomerReport(String customerName) {
        Customer foundCustomer = findCustomer(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
            return;
        }
        String result = foundCustomer.getReport();
        System.out.println(result);
    }

    void rentVideo(Customer foundCustomer, String videoTitle) {
        Video foundVideo = null;
        for (Video video : videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }

        if (foundVideo == null) return;

        Rental rental = new Rental(foundVideo);
        foundVideo.setRented(true);

        foundCustomer.addRental(rental);
    }

    void registerCustomer(String name) {
        Customer customer = new Customer(name);
        customers.add(customer);
    }

    void registerVideo(String title, int videoType, int priceCode) {
        Date registeredDate = new Date();
        Video video = new Video(title, videoType, priceCode, registeredDate);
        videos.add(video);
    }

    public void init() {
        Customer james = new Customer("James") ;
        Customer brown = new Customer("Brown") ;
        customers.add(james) ;
        customers.add(brown) ;

        Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
        Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
        videos.add(v1) ;
        videos.add(v2) ;

        Rental r1 = new Rental(v1) ;
        Rental r2 = new Rental(v2) ;

        james.addRental(r1) ;
        james.addRental(r2) ;
    }
}