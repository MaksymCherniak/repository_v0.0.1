package BookingTool.Entity;

import BookingTool.Model.LocalModel.User;
import BookingTool.Model.LocalModel.Wagon;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int train;
    @ManyToOne
    @JoinColumn(name = "wagon_id")
    private Wagon wagon;
    @Column
    private int seat;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTrain() {
        return train;
    }

    public int getSeat() {
        return seat;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public Long getIndex() {
        return id;
    }

    public void setTrain(int train) {
        this.train = train;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket other = (Ticket) obj;
        return train == other.getTrain() && wagon.equals(other.getWagon()) && seat == other.getSeat() && user.equals(other.getUser());
    }

    @Override
    public String toString() {
        return "Ticket: " + id + ", Train: " + train + ", " + wagon.printForTicket() + ", Seat: " +
                seat + " -- " + user.toString();
    }
}