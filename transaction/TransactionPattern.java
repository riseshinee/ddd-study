import java.util.UUID;
import java.sql.Timestamp;

public class TransactionPattern {
  // ...

  /**
   * 실패 예제: 유저 테이블의 업데이트 + 로그에 데이터를 insert해야하는데 중간에 문제가 발생하면 데이터 일관성이 무너짐
   */
  public void executeError(UUID userId, Timestamp visitedOn) {
    _db.execute("UPDATE Users SET last_visit=? WHERE user_id=?",
            visitedOn, userId);
    _db.execute("INSERT INTO VisitsLog(user_id, visit_date) VALUES(?, ?)",
            userId, visitedOn);
  }

  /**
   *  예제: 두 데이터를 변경하는 하나의 트랜잭션을 만들기
   */
  public void executeAll(UUID userId, Timestamp visitedOn) {
    try {
      _db.startTransaction();

      _db.execute("UPDATE Users SET last_visit=? WHERE user_id=?",
              visitedOn, userId);

      _db.execute("INSERT INTO VisitsLog(user_id, visit_date) VALUES(?, ?)",
              userId, visitedOn);

      _db.commit();
    } catch (Exception e) {
      _db.rollback();
      throw e;
    }
  }
  
}
