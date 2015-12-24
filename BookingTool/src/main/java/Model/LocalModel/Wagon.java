package Model.LocalModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 10.12.2015.
 */
public class Wagon {
    private WagonType wagonType;
    private Integer number;
    private Integer freeSeats;
    private final static Integer comfortableWagonCapacity = 16;
    private final static Integer compartmentWagonCapacity = 32;
    private final static Integer economyWagonCapacity = 54;
    private static List<Integer> comfortableWagonList = new ArrayList<Integer>();
    private static List<Integer> compartmentWagonList = new ArrayList<Integer>();
    private static List<Integer> economyWagonList = new ArrayList<Integer>();
    public Wagon(){
    }

    public void setFreeSeats(Integer freeSeats) { this.freeSeats = freeSeats; }
    public void setNumber(Integer number) { this.number = number; }
    public void setWagonType(String type) {
        if (type.equalsIgnoreCase("comfortable")){
            wagonType = WagonType.COMFORTABLE;
        } else if (type.equalsIgnoreCase("compartment")){
            wagonType = WagonType.COMPARTMENT;
        } else if (type.equalsIgnoreCase("economy")){
            wagonType = WagonType.ECONOMY;
        }
    }

    public Integer getFreeSeats() { return freeSeats; }
    public Integer getNumber() { return number; }
    public WagonType getWagonType() { return wagonType; }
    public static List getComfortableWagonSeats() {
        for (int i = 0; i < comfortableWagonCapacity; i++){
            comfortableWagonList.add(i);
        }
        return comfortableWagonList;
    }
    public static List<Integer> getCompartmentWagonList() {
        for (int i = 0; i < compartmentWagonCapacity; i++){
            compartmentWagonList.add(i);
        }
        return compartmentWagonList;
    }
    public static List<Integer> getEconomyWagonList() {
        for (int i = 0; i < economyWagonCapacity; i++){
            economyWagonList.add(i);
        }
        return economyWagonList;
    }

    @Override
    public String toString() {
        Integer totalSeats = 0;
        if (String.valueOf(wagonType).equalsIgnoreCase("comfortable")){
            totalSeats = comfortableWagonCapacity;
        } else if (String.valueOf(wagonType).equalsIgnoreCase("compartment")){
            totalSeats = compartmentWagonCapacity;
        } else if (String.valueOf(wagonType).equalsIgnoreCase("economy")){
            totalSeats = economyWagonCapacity;
        }
        return "Wagon number: " + number + " is " + wagonType + ". Total seats: " + totalSeats + ", Free seats: " + freeSeats;
    }
}
