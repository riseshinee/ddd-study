public class CampaignPublishingSaga {
  private final ICampaignRepository repository;
  private final List<IDomainEvent> events;

  public CampaignPublishingSaga(ICampaignRepository repository, List<IDomainEvent> events) {
    this.repository = repository;
    this.events = events;
  }

  public void process(CampaignActivated activated) {
    var campaign = repository.load(activated.getCampaignId());
    var advertisingMaterials = campaign.generateAdvertisingMaterials();
    var commandIssuedEvent = new CommandIssuedEvent(
            Target.PUBLISHING_SERVICE,
            new SubmitAdvertisementCommand(activated.getCampaignId(), advertisingMaterials)
    );

    events.add(activated);
    events.add(commandIssuedEvent);
  }

  public void process(PublishingConfirmed confirmed) {
    var commandIssuedEvent = new CommandIssuedEvent(
            Target.CAMPAIGN_AGGREGATE,
            new TrackConfirmation(confirmed.getCampaignId(), confirmed.getConfirmationId())
    );

    events.add(confirmed);
    events.add(commandIssuedEvent);
  }

  public void process(PublishingRejected rejected) {
    var commandIssuedEvent = new CommandIssuedEvent(
            Target.CAMPAIGN_AGGREGATE,
            new TrackRejection(rejected.getCampaignId(), rejected.getRejectionReason())
    );

    events.add(rejected);
    events.add(commandIssuedEvent);
  }
}
