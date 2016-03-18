package BookingTool.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @Column(name = "route_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int routeNumber;
    @Column
    private String leavingStation;
    @Column
    private String arrivalStation;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stations> stations;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Train> train;

    public Route() {
    }

    public long getId() {
        return id;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public void setLeavingStation(String leavingStation) {
        this.leavingStation = leavingStation;
    }

    public Set<Stations> getStations() {
        return stations;
    }

    public void setStations(Set<Stations> stations) {
        this.stations = stations;
    }

    public Set<Train> getTrain() {
        return train;
    }

    public void setTrain(Set<Train> train) {
        this.train = train;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getLeavingStation() {
        return leavingStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    @Override
    public String toString() {
        return "Route number: " + routeNumber + ", " + leavingStation + "-" + arrivalStation;
    }
}
