package BookingTool.Entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "stations")
public class Stations {
    @Id
    @Column(name = "station_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String station;
    @Column
    private LocalTime arrivalTime;
    @Column
    private LocalTime leavingTime;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public Stations() {
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setLeavingTime(LocalTime leavingTime) {
        this.leavingTime = leavingTime;
    }

    public String getStation() {
        return station;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getLeavingTime() {
        return leavingTime;
    }

    @Override
    public String toString() {
        return "Stations: " + station + " || Arrival time: " + arrivalTime + " || Leaving time: " + leavingTime;
    }
}
