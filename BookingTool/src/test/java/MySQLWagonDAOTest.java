import DAO.MySQLWagonDAO;
import Model.LocalModel.Ticket;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MySQLWagonDAOTest {
    private MySQLWagonDAO mySQLWagonDAO;
    private EntityManager entityManager;

    @Before
    public void initializeDependencies() {
        entityManager = Persistence.createEntityManagerFactory("test").
                createEntityManager();
        mySQLWagonDAO = new MySQLWagonDAO();
        mySQLWagonDAO.setEntityManager(entityManager);
    }

    @Test
    public void testUpdate() {
        Wagon wagon = new Wagon();
        wagon.setNumber(1);
        wagon.setWagonType("COMFORTABLE");

        mySQLWagonDAO.insertWagon(wagon);
        boolean wagonCheck = mySQLWagonDAO.insertWagon(wagon);

        assertFalse(wagonCheck);

        boolean findCheck;
        wagon = mySQLWagonDAO.findWagon(wagon.getNumber());
        if (wagon == null){
            findCheck = false;
        } else {
            findCheck = true;
        }
        assertTrue(findCheck);

        Ticket ticket = new Ticket();
        ticket.setTrain(10);
        ticket.setWagon(wagon);
        ticket.setUser(new User("UserSurname", "UserName"));
        ticket.setSeat(1);

        mySQLWagonDAO.updateSeat(ticket);
        boolean updateSeatChecker = false;
        if (mySQLWagonDAO.checkSeatAvailable(ticket)) {
            updateSeatChecker = mySQLWagonDAO.updateSeat(ticket);
        }
        assertFalse(updateSeatChecker);

        mySQLWagonDAO.updateWagon(ticket);
        boolean updateWagonChecker = mySQLWagonDAO.updateWagon(ticket);
        assertFalse(updateWagonChecker);
    }
}
