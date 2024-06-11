/**
 * 포트 (Port): IMessaging 인터페이스
 */
public interface IMessaging {
  void publish(Message payload);
  void subscribe(Message type, Runnable callback);
}

/**
 * 어댑터 (Adapter): SQSBus 클래스
 */
public class SQSBus implements IMessaging {
  @Override
  public void publish(Message payload) {
    // AWS SQS에 메시지 게시하는 구현
  }

  @Override
  public void subscribe(Message type, Runnable callback) {
    // AWS SQS로부터 메시지를 구독하는 구현
  }
}