package BookingTool.Controller;

public interface IControllerStaticValues {
    // ------------------- User ---------------------

    String NAME = "name";
    String SURNAME = "surname";
    String ID = "id";

    // ------------------- Route --------------------

    String ROUTE = "route";
    String ROUTE_NUMBER = "routeNumber";
    String ROUTE_YEAR = "routeYY";
    String ROUTE_MONTH = "routeMM";
    String ROUTE_DAY = "routeDD";
    String ROUTE_LIST_OF_ROUTES = "listOfRoutes";

    // ------------------- Train --------------------

    String TRAIN = "train";
    String TRAIN_DAYS = "days";
    String TRAIN_START_YEAR = "startYY";
    String TRAIN_START_MONTH = "startMM";
    String TRAIN_START_DAY = "startDD";

    // ------------------ Stations -------------------

    String STATION = "station";
    String STATION_ID = "station_id";
    String STATION_LEAVING_STATION = "leavingStation";
    String STATION_ARRIVAL_STATION = "arrivalStation";
    String STATION_LEAVING_HOUR = "leavingHH";
    String STATION_LEAVING_MINUTE = "leavingMM";
    String STATION_ARRIVAL_HOUR = "arrivalHH";
    String STATION_ARRIVAL_MINUTE = "arrivalMM";
    String STATION_LIST_OF_STATIONS = "listOfStations";

    // ------------------- Wagon --------------------

    String SEAT = "seat";
    String WAGON_COMFORTABLE = "comfortable";
    String WAGON_COMPARTMENT = "compartment";
    String WAGON_ECONOMY = "economy";

    // ------------------- PAGES --------------------

    String INFO = "info";
    String PAGE_INFO = "pageInfo";
    String PAGE_PRINT_ROUTES = "printRoutes";
    String PAGE_BOOK_SEAT = "bookSeat";
    String PAGE_INSERT_STATION = "insertStation";
    String PAGE_PRINT_WAGON = "printWagon";
    String PAGE_PRINT_TRAIN = "printTrain";
    String PAGE_GET_ALL_ROUTES = "getAllRoutes";
    String PAGE_GET_STATIONS_BY_ROUTE = "getStationsByRoute";
    String PAGE_GET_ALL_ROUTES_FOR_ACTIONS = "getAllRoutesForAction";
    String PAGE_EDIT_STATION = "editStation";
    String PAGE_ROUTE_ACTIONS = "routeActions";
    String PAGE_INSERT_TRAIN = "insertTrain";
    String PAGE_INSERT_WAGON = "insertWagon";
    String PAGE_MAIN = "main";
    String PAGE_INSERT_ROUTE = "insertRoute";

    // ----------------------------------------------
}
