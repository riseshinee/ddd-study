/**
 * 서비스 계층: 비지니스 로직 계층으로의 관문, 프레젠테이션 계층과 비지니스 로직 계층의 결합도를 낮춤
 */
public class UserService {
  private Database _db;

  public UserService(Database db) {
    this._db = db;
  }

  public OperationResult create(ContactDetails contactDetails) {
    OperationResult result = null;
    Connection connection = null;

    try {
      connection = _db.startTransaction();

      User user = new User();
      user.setContactDetails(contactDetails);
      user.save(connection);

      _db.commit(connection);
      result = OperationResult.success();
    } catch (Exception ex) {
      if (connection != null) {
        _db.rollback(connection);
      }
      result = OperationResult.exception(ex);
    }

    return result;
  }
}
