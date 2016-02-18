package BookingTool.DAO.Repository;

import BookingTool.Model.LocalModel.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
