/**
 *  분산 시스템간 트랜잭션 일관성 보장 방법: 프로세스 관리자 패턴
 */
public class BookingProcessManager {
  private final List<DomainEvent> events = new ArrayList<>();
  private BookingId id;
  private Destination destination;
  private TripDefinition parameters;
  private EmployeeId traveler;
  private Route route;
  private final List<Route> rejectedRoutes = new ArrayList<>();
  private final IRoutingService routing;

  public BookingProcessManager(IRoutingService routing) {
    this.routing = routing;
  }

  public void initialize(Destination destination, TripDefinition parameters, EmployeeId traveler) {
    this.destination = destination;
    this.parameters = parameters;
    this.traveler = traveler;
    this.route = routing.calculate(destination, parameters);

    RouteGeneratedEvent routeGenerated = new RouteGeneratedEvent(id, route);
    CommandIssuedEvent commandIssuedEvent = new CommandIssuedEvent(new RequestEmployeeApproval(traveler, route));

    events.add(routeGenerated);
    events.add(commandIssuedEvent);
  }

  public void process(RouteConfirmed confirmed) {
    CommandIssuedEvent commandIssuedEvent = new CommandIssuedEvent(new BookFlights(route, parameters));

    events.add(confirmed);
    events.add(commandIssuedEvent);
  }

  public void process(RouteRejected rejected) {
    CommandIssuedEvent commandIssuedEvent = new CommandIssuedEvent(new RequestRerouting(traveler, route));

    events.add(rejected);
    events.add(commandIssuedEvent);
  }

  public void process(ReroutingConfirmed confirmed) {
    rejectedRoutes.add(route);
    route = routing.calculateAltRoute(destination, parameters, rejectedRoutes);

    RouteGeneratedEvent routeGenerated = new RouteGeneratedEvent(id, route);
    CommandIssuedEvent commandIssuedEvent = new CommandIssuedEvent(new RequestEmployeeApproval(traveler, route));

    events.add(confirmed);
    events.add(routeGenerated);
    events.add(commandIssuedEvent);
  }

  public void process(FlightBooked booked) {
    CommandIssuedEvent commandIssuedEvent = new CommandIssuedEvent(new BookHotel(destination, parameters));

    events.add(booked);
    events.add(commandIssuedEvent);
  }

  // Getters and other methods can be added here
}
