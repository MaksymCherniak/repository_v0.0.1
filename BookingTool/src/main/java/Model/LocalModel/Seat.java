package Model.LocalModel;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@NamedQuery(name = "Seat.getAll", query = "SELECT c from Seat c")
public class Seat {
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int number;
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    @Column
    private Integer ticket;
    @ManyToOne
    @JoinColumn(name = "wagon_id")
    private Wagon wagon;

    public SeatStatus getStatus() {
        return status;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("free")) {
            this.status = SeatStatus.FREE;
        } else if (status.equalsIgnoreCase("occupied")) {
            this.status = SeatStatus.OCCUPIED;
        }
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTicket(Integer ticket) {
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