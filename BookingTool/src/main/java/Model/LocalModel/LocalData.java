package Model.LocalModel;

import DAO.ILocalData;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Max on 01.12.2015.
 */
public class LocalData implements ILocalData{
    public static Map<Integer, AvailabilitySeats> collectionOfAvailabilitySeats = new HashMap<Integer, AvailabilitySeats>();
    private static Map<Integer, Integer> collectionOfSeatsOccupiedByUsers = new HashMap<Integer, Integer>();
    public static int[] seatChecker = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    public void create() {
        for (int i = 1; i <= 20; i++){
            collectionOfAvailabilitySeats.put(i, AvailabilitySeats.FREE);
            collectionOfSeatsOccupiedByUsers.put(i, null);
        }
    }

    public void read() {
        Iterator<Map.Entry<Integer, AvailabilitySeats>> iterator =
                collectionOfAvailabilitySeats.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, AvailabilitySeats> pair = iterator.next();
            System.out.println("Seat " + pair.getKey() + " is " + pair.getValue());
        }
    }

    public void update(int seatNumber, User user) {
        Iterator<Map.Entry<Integer, AvailabilitySeats>> iteratorSeats =
                collectionOfAvailabilitySeats.entrySet().iterator();
        Iterator<Map.Entry<Integer, Integer>> iteratorUsers =
                collectionOfSeatsOccupiedByUsers.entrySet().iterator();
        while (iteratorSeats.hasNext() && iteratorUsers.hasNext()){
            Map.Entry<Integer, AvailabilitySeats> pairSeats = iteratorSeats.next();
            Map.Entry<Integer, Integer> pairUsers = iteratorUsers.next();
            if (pairSeats.getKey() == seatNumber){
                pairSeats.setValue(AvailabilitySeats.OCCUPIED);
                pairUsers.setValue(user.getIndex());
            }
        }
    }

    public void delete() {
    }

    public List<LocalData> getAll() {
        return null;
    }
}
