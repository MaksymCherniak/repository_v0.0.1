package BookingTool.Servlets;

import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Seat;
import BookingTool.Entity.Train;
import BookingTool.Model.LocalModel.Wagon;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrintWagon", urlPatterns = "/printWagon.do")
public class PrintWagon extends HttpServlet {
    private IWagonDAO iWagonDAO;
    private ITrainDAO iTrainDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iTrainDAO = (ITrainDAO) webApplicationContext.getBean("ITrainDAO");
        iWagonDAO = (IWagonDAO) webApplicationContext.getBean("IWagonDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Long trainId = Long.parseLong(request.getParameter("train"));
        Wagon wagon = iWagonDAO.getWagonById(id);
        Train train = iTrainDAO.getTrainById(trainId);
        List<Seat> listOfSeats = iWagonDAO.getAllSeats(wagon);
        request.setAttribute("train", train);
        request.setAttribute("listOfSeats", listOfSeats);
        request.getRequestDispatcher("printWagon.jsp").forward(request, response);
    }
}
