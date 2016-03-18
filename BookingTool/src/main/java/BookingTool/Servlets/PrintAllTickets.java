package BookingTool.Servlets;

import BookingTool.DAO.Service.ITicketDAO;
import BookingTool.Entity.Ticket;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintAllTickets extends HttpServlet {
    private static Logger log = Logger.getLogger(BuyTicket.class.getName());
    private WebApplicationContext webApplicationContext;
    private ITicketDAO iTicketDAO;
    private static final String PAGE_OK = "printTicket.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iTicketDAO = (ITicketDAO) webApplicationContext.getBean("ITicketDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.equals("")) {
            try {
                Ticket ticket = iTicketDAO.findTicketByID(Long.parseLong(id));
                if (ticket != null) {
                    request.setAttribute("ticket", ticket);
                    request.getRequestDispatcher(PAGE_OK).forward(request, response);
                    return;
                }
            } catch (Exception e) {
                log.log(Level.INFO, "Exception: ", e);
            }
        }
        request.setAttribute(IServletResultInfo.INFO, "Ticket not found");
        request.getRequestDispatcher(IServletResultInfo.PAGE_INFO).forward(request, response);
    }
}
