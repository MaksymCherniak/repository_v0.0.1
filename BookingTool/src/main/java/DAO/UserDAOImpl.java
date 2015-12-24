package DAO;

import Model.LocalModel.User;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 20.12.2015.
 */
public class UserDAOImpl implements IUserDAO{
    private static EntityManager entityManager;
    public UserDAOImpl(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-unit");
        entityManager = emf.createEntityManager();
    }
    public int insertUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {}
        finally {
        }
        return 0;
    }

    public User findUser(String id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return  (User) session.load(User.class, id);
        } catch (Exception e) {
            return null;
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {}
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List getAllUsers() {
        Session session = null;
        List<User> users = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
        } catch (Exception e) {}
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }
}
