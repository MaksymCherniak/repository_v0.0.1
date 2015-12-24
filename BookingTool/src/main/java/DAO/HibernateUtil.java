package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;


public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    public HibernateUtil(){
        try {
            setUp();
        } catch (Exception e) {}
    }
    public static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("hibernate.cfg.xml"))
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public static SessionFactory getSessionFactory() {
        try {
            setUp();
        } catch (Exception e) {}
        return sessionFactory;
    }
}
