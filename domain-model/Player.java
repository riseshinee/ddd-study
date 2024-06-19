
public class Player {
  private UUID id;
  private int points;

  public Player(UUID id, int points) {
    this.id = id;
    this.points = points;
  }

  public void applyBonus(int percentage) {
    this.points *= 1 + percentage / 100.0;
  }
}

public class ApplyBonus {
  private PlayerRepository repository;

  public void execute(UUID playerId, int percentage) {
    Player player = repository.load(playerId);
    player.applyBonus(percentage);
    repository.save(player);
  }
}