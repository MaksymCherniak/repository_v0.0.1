package DAO;

public class Factory {
    private static IBookDAO iBookDAO = null;
    private static Factory instance = null;

    public static Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public static IBookDAO getXmlIBookDAO(){
        if (iBookDAO == null){
            iBookDAO = new XmlBookDAO();
        }
        return iBookDAO;
    }
}
