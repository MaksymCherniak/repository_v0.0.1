package DAO;

import Model.LocalModel.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 20.12.2015.
 */
public class UserDAOImpl implements IUserDAO{
    public int insertUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {}
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return 0;
    }

    public User findUser(String id) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.load(User.class, id);
        } catch (Exception e) {}
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
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
