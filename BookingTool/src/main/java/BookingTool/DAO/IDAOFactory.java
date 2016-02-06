package BookingTool.DAO;

public interface IDAOFactory {
    IRouteDAO getIRouteDAO();

    IUserDAO getIUserDAO();

    IWagonDAO getIWagonDAO();

    ITicketDAO getITicketDAO();
}