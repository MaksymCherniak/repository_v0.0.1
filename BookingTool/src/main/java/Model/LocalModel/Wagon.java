package Model.LocalModel;

import DAO.XmlUserDAO;
import DAO.XmlWagonDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 10.12.2015.
 */
public class Wagon {
    private int number;
    public final static int[] seatChecker = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    public Wagon(){
        //this.number = number;
    }

    public int getNumber() { return number; }

    public static int[] getSeatChecker() { return seatChecker; }

    private List<Integer> initList(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < seatChecker.length; i ++) {
            list.add(i + 1);
        }
        return list;
    }
}
