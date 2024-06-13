public class ManagementAPI {
  // ...
  private final IMessageBus messageBus;
  private final ICampaignRepository repository;

  // Constructor
  public ManagementAPI(IMessageBus messageBus, ICampaignRepository repository) {
    this.messageBus = messageBus;
    this.repository = repository;
  }

  public ExecutionResult deactivateCampaign(CampaignId id, String reason) {
    try {
      Campaign campaign = repository.load(id);
      campaign.deactivate(reason);
      repository.commitChanges(campaign);

      List<IDomainEvent> events = campaign.getUnpublishedEvents();
      for (IDomainEvent e : events) {
        messageBus.publish(e);
      }
      campaign.clearUnpublishedEvents();
    } catch (Exception ex) {
      // 예외 처리 코드
    }
    return new ExecutionResult();
  }
}