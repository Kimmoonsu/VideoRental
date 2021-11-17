import java.util.Date;

public class Rental {
	public static final int VHS_RENTAL_DAYS_LIMIT = 5;
	public static final int CD_RENTAL_DAYS_LIMIT = 3;
	public static final int DVD_RENTAL_DAYS_LIMIT = 2;

	public static final int STATUS_RENTED = 0;
	public static final int STATUS_RETURNED = 1;

	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public boolean isRented() {
		return video.isRented();
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == STATUS_RENTED ) {
			this.status = STATUS_RETURNED;
			returnDate = new Date() ;
			video.setRented(false);
		}
	}

	public Date getRentDate() {
		return rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;
		long diff;
		if (getStatus() == STATUS_RETURNED) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		if ( daysRented <= 2) return limit ;

		switch (video.getVideoType()) {
			case Video.VHS:
				limit = VHS_RENTAL_DAYS_LIMIT;
				break;
			case Video.CD:
				limit = CD_RENTAL_DAYS_LIMIT;
				break;
			case Video.DVD:
				limit = DVD_RENTAL_DAYS_LIMIT;
				break;
		}
		return limit;
	}

	public boolean hasVideo(String videoTitle) {
		return videoTitle.equals(video.getTitle());
	}

	public int getPriceCode() {
		return video.getPriceCode();
	}

	public int getLateReturnPointPenalty() {
		return video.getLateReturnPointPenalty();
	}

	public String getTitle() {
		return video.getTitle();
	}
}
