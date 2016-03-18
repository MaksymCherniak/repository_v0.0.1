package BookingTool.Servlets;

import BookingTool.DAO.Service.IRouteDAO;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectRoute extends HttpServlet {
    private static Logger log = Logger.getLogger(SelectRoute.class.getName());
    private IRouteDAO iRouteDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(config.getServletContext());
        iRouteDAO = (IRouteDAO) webApplicationContext.getBean("IRouteDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private LocalDate getLeavingDate(String[] leavingDate) {
        try {
            return LocalDate.of(Integer.parseInt(leavingDate[2]), Integer.parseInt(leavingDate[1]), Integer.parseInt(leavingDate[0]));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }

    public void setiRouteDAO(IRouteDAO iRouteDAO) {
        this.iRouteDAO = iRouteDAO;
    }
}
