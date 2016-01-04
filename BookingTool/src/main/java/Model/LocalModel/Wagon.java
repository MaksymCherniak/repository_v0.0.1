package Model.LocalModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wagon")
@NamedQuery(name = "Wagon.getAll", query = "SELECT c from Wagon c")
public class Wagon {
    @Id
    @Column(name = "wagon_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int number;
    @Column
    private String wagonType;
    @Column
    private int totalSeats;
    @Column
    private int freeSeats;
    @OneToMany(mappedBy = "wagon")
    private Set<Seat> seats;
    @OneToMany(mappedBy = "wagon")
    private Set<Ticket> tickets;

    private final static Integer comfortableWagonCapacity = 18;
    private final static Integer compartmentWagonCapacity = 36;
    private final static Integer economyWagonCapacity = 54;
    private static List<Integer> comfortableWagonList = new ArrayList<Integer>();
    private static List<Integer> compartmentWagonList = new ArrayList<Integer>();
    private static List<Integer> economyWagonList = new ArrayList<Integer>();

    public Wagon() {
    }

    public int getId() {
        return id;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setWagonType(String type) {
        if (type.equalsIgnoreCase("comfortable")) {
            wagonType = String.valueOf(WagonType.COMFORTABLE);
            totalSeats = freeSeats = comfortableWagonCapacity;
        } else if (type.equalsIgnoreCase("compartment")) {
            wagonType = String.valueOf(WagonType.COMPARTMENT);
            totalSeats = freeSeats = compartmentWagonCapacity;
        } else if (type.equalsIgnoreCase("economy")) {
            wagonType = String.valueOf(WagonType.ECONOMY);
            totalSeats = freeSeats = economyWagonCapacity;
        }
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public Integer getNumber() {
        return number;
    }

    public String getWagonType() {
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
    public String toString() {
        if (String.valueOf(wagonType).equalsIgnoreCase("comfortable")) {
            totalSeats = comfortableWagonCapacity;
        } else if (String.valueOf(wagonType).equalsIgnoreCase("compartment")) {
            totalSeats = compartmentWagonCapacity;
        } else if (String.valueOf(wagonType).equalsIgnoreCase("economy")) {
            totalSeats = economyWagonCapacity;
        }
        return "Wagon number: " + number + " is " + wagonType + ". Total seats: " + totalSeats + ", Free seats: " + freeSeats;
    }
}