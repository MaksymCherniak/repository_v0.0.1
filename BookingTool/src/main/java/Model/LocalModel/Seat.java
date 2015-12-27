package Model.LocalModel;

/**
 * Created by Max on 21.12.2015.
 */
public class Seat {
    private AvailabilitySeats status;
    private Integer number;
    private String ticket = null;

    public AvailabilitySeats getStatus() {
        return status;
    }

    public Integer getNumber() {
        return number;
    }

    public String getTicket() {
        return ticket;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("free")) {
            this.status = AvailabilitySeats.FREE;
        } else if (status.equalsIgnoreCase("occupied")) {
            this.status = AvailabilitySeats.OCCUPIED;
        }
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        if (ticket == null) {
            return "Seat number " + number + " is " + status;
        } else {
            return "Seat number " + number + " is " + status + ", ticket: " + ticket;
        }
    }
}
