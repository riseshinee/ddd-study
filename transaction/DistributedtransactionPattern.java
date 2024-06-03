import java.util.UUID;
import java.sql.Timestamp;

public class DistributedTransactionPattern {
  // ...

  /**
   * 메세지 버스에 메시지를 발행하여 다른 컴포넌트에 변경사항을 알려 다른 트랜잭션을 통해 일관성을 유지
   * @param userId
   * @param visitedOn
   */
  public void executeMessage(UUID userId, LocalDateTime visitedOn) {
    db.execute("UPDATE Users SET last_visit = ? WHERE user_id = ?", visitedOn, userId);
    messageBus.publish("VISITS_TOPIC", new Object() {
      public UUID UserId = userId;
      public LocalDateTime VisitDate = visitedOn;
    });
  }

  /**
   * 기존 카운터 값을 먼저 조회하여 호출부에서 업데이트 될 카운터 값을 제공하기 (멱등성 유지)
   * @param userId
   * @param visits
   */
  public void executeCount1(UUID userId, long visits) {
    db.execute("UPDATE Users SET visits = ? WHERE user_id = ?", visits, userId);
  }

  /**
   * 호출부에서 처음 읽은 카운트 값과 동일할 때만 업데이트 트랜잭션 수행하기 (낙관적 동시성 제어)
   * @param userId
   * @param expectedVisits
   */
  public void executeCount2(UUID userId, long expectedVisits) {
    db.execute("UPDATE Users SET visits = visits + 1 WHERE user_id = ? AND visits = ?", userId, expectedVisits);
  }

}
