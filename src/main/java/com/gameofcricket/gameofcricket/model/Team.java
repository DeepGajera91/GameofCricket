package com.gameofcricket.gameofcricket.model;

import java.util.Locale;

public class Team {
  private String teamName;
  private Player[] players = new Player[11];

  public Team(String teamName) {
    this.teamName = teamName.toUpperCase(Locale.ROOT);

    int ratting = 100;

    for (int i = 0; i < 6; i++) {
      players[i] = new Player(teamName + i, i, PlayerType.BATSMAN, ratting);
      ratting -= 5;
    }
    for (int i = 6; i < 11; i++) {
      players[i] = new Player(teamName + i, i, PlayerType.BOWLER, ratting);
      ratting -= 7;
    }
  }

  public Team() {}

  public String getTeamname() {
    return this.teamName;
  }

  public Player[] getPlayers() {
    return this.players;
  }

  @Override
  public String toString() {
    return this.teamName;
  }
}
