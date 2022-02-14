package com.gameofcricket.gameofcricket;

public class Player {
  private final int playerID;
  private final String playerName;
  private final PlayerType playerType;
  private final int rating;

  public Player(String playerName, int playerID, PlayerType playerType) {
    this.playerName = playerName;
    this.playerID = playerID;
    this.playerType = playerType;
    this.rating = ((playerType == PlayerType.BATSMAN) ? 90 : 50);
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public int getPlayerID() {
    return playerID;
  }

  public PlayerType getPlayerType() {
    return playerType;
  }

  public int getRating() {
    return rating;
  }

  @Override
  public String toString() {
    return this.playerName;
  }
}
