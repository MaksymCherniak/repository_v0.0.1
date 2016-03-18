import BookingTool.DAO.Impl.MySQLWagonDAO;
import BookingTool.Entity.Ticket;
import BookingTool.Entity.User;
import BookingTool.Entity.Wagon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class WagonDAO {
    private MySQLWagonDAO mySQLWagonDAO;
    private EntityManager entityManager;
    private Wagon wagon;
    private Ticket ticket;

    @Before
    public void initializeDependencies() {
        entityManager = Persistence.createEntityManagerFactory("test").
                createEntityManager();
        mySQLWagonDAO = new MySQLWagonDAO();
       // mySQLWagonDAO.setEntityManager(entityManager);

        wagon = new Wagon();
        wagon.setNumber(1);
        wagon.setWagonType("COMFORTABLE");

        ticket = new Ticket();
        ticket.setTrain(10);
        ticket.setWagon(wagon);
        ticket.setUser(new User("UserSurname", "UserName"));
        ticket.setSeat(1);
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @Test
    public void addDuplicatedWagonIsImpossible() {
        mySQLWagonDAO.insertWagon(wagon);
        assertFalse("Duplicated wagon added", mySQLWagonDAO.insertWagon(wagon));

        List<Wagon> wagonList = mySQLWagonDAO.getAllWagons();
        assertTrue("Wagon was not added", wagonList.contains(wagon));
    }

    @Test
    public void whenAddTheWagonExists() {

    }

    @Test
    public void doubleBookedSeatIsImpossible() {
        mySQLWagonDAO.updateSeat(ticket);
        assertFalse("Double booked seat", mySQLWagonDAO.updateSeat(ticket));
    }
}
