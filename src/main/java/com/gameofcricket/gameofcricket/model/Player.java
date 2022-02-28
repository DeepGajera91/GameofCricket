package com.gameofcricket.gameofcricket.model;

public class Player {
  private final int id;
  private final String name;
  private final PlayerType type;
  private final int rating;

  public Player(String name, int id, PlayerType type, int rating) {
    this.name = name;
    this.id = id;
    this.type = type;
    this.rating = rating;
  }

  public String getPlayerName() {
    return this.name;
  }

  public int getPlayerID() {
    return this.id;
  }

  public PlayerType getPlayerType() {
    return this.type;
  }

  public int getRating() {
    return this.rating;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
