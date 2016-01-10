import DAO.MySQLWagonDAO;
import Model.LocalModel.Ticket;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Persistence;

import static org.junit.Assert.assertFalse;

public class MySQLWagonDAOTest {
    private MySQLWagonDAO mySQLWagonDAO;

    @Before
    public void initializeDependencies() {
        mySQLWagonDAO = new MySQLWagonDAO();
        mySQLWagonDAO.entityManager = Persistence.createEntityManagerFactory("test").
                createEntityManager();
    }

    @Test
    public void testUpdate() {
        Wagon wagon = new Wagon();
        wagon.setNumber(1);
        wagon.setWagonType("COMFORTABLE");
        Wagon wagon1 = new Wagon();
        wagon1.setNumber(1);
        wagon1.setWagonType("COMFORTABLE");

        mySQLWagonDAO.insertWagon(wagon);
        boolean wagonCheck = mySQLWagonDAO.insertWagon(wagon1);

        assertFalse(wagonCheck);

        Ticket ticket = new Ticket();
        Ticket ticket2 = new Ticket();
        wagon = mySQLWagonDAO.findWagon(1);

        ticket.setTrain(10);
        ticket.setWagon(wagon);
        ticket.setUser(new User("jsdhk", "askdla"));
        ticket.setSeat(1);

        ticket2.setTrain(10);
        ticket2.setWagon(wagon);
        ticket2.setUser(new User("jsdhk", "askdla"));
        ticket2.setSeat(1);

        mySQLWagonDAO.updateSeat(ticket);
        boolean seatChecker = false;
        if (mySQLWagonDAO.checkSeatAvailable(ticket2)) {
            seatChecker = mySQLWagonDAO.updateSeat(ticket2);
        }
        assertFalse(seatChecker);
    }
}
