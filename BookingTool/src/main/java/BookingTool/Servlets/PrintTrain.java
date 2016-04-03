package BookingTool.Servlets;

import BookingTool.DAO.Service.ITrainDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Entity.Train;
import BookingTool.Model.LocalModel.Wagon;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class PrintTrain extends HttpServlet {
    private static Logger log = Logger.getLogger(PrintTrain.class.getName());
    private IWagonDAO iWagonDAO;
    private ITrainDAO iTrainDAO;
    private WebApplicationContext webApplicationContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iWagonDAO = (IWagonDAO) webApplicationContext.getBean("IWagonDAO");
        iTrainDAO = (ITrainDAO) webApplicationContext.getBean("ITrainDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Train train = iTrainDAO.getTrainById(id);
        List<Wagon> listOfWagons = iWagonDAO.getWagonsByTrain(train);
        request.setAttribute("train", train);
        request.setAttribute("listOfWagons", listOfWagons);
        request.getRequestDispatcher("printTrain.jsp").forward(request, response);
    }
}
