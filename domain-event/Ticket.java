/**
 * 도메인 이벤트 예제
 */
public class Ticket {
  // ...
  private List<DomainEvent> domainEvents;
  // ...

  public void execute(RequestEscalation cmd) {
    if (!this.isEscalated && this.getRemainingTimePercentage() <= 0) {
      this.isEscalated = true;
      TicketEscalated escalatedEvent = new TicketEscalated(id);
      domainEvents.add(escalatedEvent);
    }
  }

  // Getter and setter methods for isEscalated and remainingTimePercentage
  // ...
}

