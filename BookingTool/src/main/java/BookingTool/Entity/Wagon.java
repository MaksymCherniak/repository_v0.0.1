package BookingTool.Entity;

import BookingTool.Entity.Enums.WagonType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wagon")
public class Wagon {
    @Id
    @Column(name = "wagon_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int number;
    @Enumerated(EnumType.STRING)
    private WagonType wagonType;
    @Column
    private int totalSeats;
    @Column
    private int freeSeats;
    @OneToMany(mappedBy = "wagon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats;
    @OneToMany(mappedBy = "wagon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private final static Integer comfortableWagonCapacity = 18;
    private final static Integer compartmentWagonCapacity = 36;
    private final static Integer economyWagonCapacity = 54;
    private static List<Integer> comfortableWagonList = new ArrayList<Integer>();
    private static List<Integer> compartmentWagonList = new ArrayList<Integer>();
    private static List<Integer> economyWagonList = new ArrayList<Integer>();

    public Wagon() {
    }

    public Long getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setWagonType(String type) {
        if (type.equalsIgnoreCase("comfortable")) {
            wagonType = WagonType.COMFORTABLE;
            totalSeats = freeSeats = comfortableWagonCapacity;
        } else if (type.equalsIgnoreCase("compartment")) {
            wagonType = WagonType.COMPARTMENT;
            totalSeats = freeSeats = compartmentWagonCapacity;
        } else if (type.equalsIgnoreCase("economy")) {
            wagonType = WagonType.ECONOMY;
            totalSeats = freeSeats = economyWagonCapacity;
        }
    }

    public Integer getNumber() {
        return number;
    }

    public WagonType getWagonType() {
        return wagonType;
    }

    public static List<Integer> getComfortableWagonSeats() {
        for (int i = 0; i < comfortableWagonCapacity; i++) {
            comfortableWagonList.add(i);
        }
        return comfortableWagonList;
    }

    public static List<Integer> getCompartmentWagonList() {
        for (int i = 0; i < compartmentWagonCapacity; i++) {
            compartmentWagonList.add(i);
        }
        return compartmentWagonList;
    }

    public static List<Integer> getEconomyWagonList() {
        for (int i = 0; i < economyWagonCapacity; i++) {
            economyWagonList.add(i);
        }
        return economyWagonList;
    }

    public String printForTicket() {
        return "Wagon number: " + number + ", wagon type: " + wagonType;
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
        Wagon other = (Wagon) obj;
        return number == other.number && wagonType.equals(other.getWagonType());
    }

    @Override
    public String toString() {
        return "Wagon number: " + number + " is " + wagonType + ". Total seats: " + totalSeats + ", Free seats: " + freeSeats;
    }
}