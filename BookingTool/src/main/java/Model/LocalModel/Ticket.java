package Model.LocalModel;

/**
 * Created by Max on 21.12.2015.
 */
public class Ticket {
    private static volatile Integer index = 0;
    private User user;
    private Integer train;
    private Integer seat;
    private Integer wagon;
    private Integer number;

    public Ticket(){
        incIndex();
        number = index;
    }

    public Integer getTrain() { return train; }
    public Integer getNumber() { return number; }
    public User getUser() { return user; }
    public Integer getSeat() { return seat; }
    public Integer getWagon() { return wagon; }
    public synchronized static Integer getIndex() {
        return index;
    }

    public void setTrain(Integer train) { this.train = train; }
    public void setNumber(Integer number) { this.number = number; }
    public void setUser(User user) { this.user = user; }
    public void setSeat(Integer seat) { this.seat = seat; }
    public void setWagon(Integer wagon) { this.wagon = wagon; }
    public static void setIndex(int index) {
        Ticket.index = index;
    }

    private synchronized static void incIndex(){ index ++; }

    @Override
    public String toString() {
        return "Ticket: " + number + ", Train: " + train + ", Wagon: " + wagon + ", Seat: " + seat
                + ", User: " + user.getLastName() + " " + user.getFirstName();
    }
}
