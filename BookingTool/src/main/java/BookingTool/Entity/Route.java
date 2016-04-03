package BookingTool.Entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

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
    @Column
    private LocalTime arrivalTime;
    @Column
    private LocalTime leavingTime;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stations> stations;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Train> train;

    public Route() {
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(LocalTime leavingTime) {
        this.leavingTime = leavingTime;
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

    public List<Stations> getStations() {
        return stations;
    }

    public void setStations(List<Stations> stations) {
        this.stations = stations;
    }

    public List<Train> getTrain() {
        return train;
    }

    public void setTrain(List<Train> train) {
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
