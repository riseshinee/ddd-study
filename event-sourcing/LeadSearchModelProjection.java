import java.util.HashSet;
import java.util.Set;

/**
 * 이벤트 소싱 패턴: lead 정보의 상태를 관리, 이벤트가 발생할 때마다 apply 메서드가 호출되어 해당 이벤트에 따라 리드 상태 업데이트
 */
public class LeadSearchModelProjection {
  private long leadId;
  private Set<String> firstNames;
  private Set<String> lastNames;
  private Set<PhoneNumber> phoneNumbers;
  private int version;

  public LeadSearchModelProjection() {
    this.firstNames = new HashSet<>();
    this.lastNames = new HashSet<>();
    this.phoneNumbers = new HashSet<>();
  }

  public void apply(LeadInitialized event) {
    this.leadId = event.getLeadId();
    this.firstNames.add(event.getFirstName());
    this.lastNames.add(event.getLastName());
    this.phoneNumbers.add(event.getPhoneNumber());
    this.version = 0;
  }

  public void apply(ContactDetailsChanged event) {
    this.firstNames.add(event.getFirstName());
    this.lastNames.add(event.getLastName());
    this.phoneNumbers.add(event.getPhoneNumber());
    this.version += 1;
  }

  public void apply(Contacted event) {
    this.version += 1;
  }

  public void apply(FollowupSet event) {
    this.version += 1;
  }

  public void apply(OrderSubmitted event) {
    this.version += 1;
  }

  public void apply(PaymentConfirmed event) {
    this.version += 1;
  }
}
