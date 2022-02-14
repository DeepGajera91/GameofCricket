package com.gameofcricket.gameofcricket;

import java.util.Locale;

public class Team {
  private final String teamName;
  private final Player[] players = new Player[11];

  public Team(String teamName) {
    this.teamName = teamName.toUpperCase(Locale.ROOT);

    for (int i = 0; i < 6; i++) {
      players[i] = new Player(teamName + i, i, PlayerType.BATSMAN);
    }
    for (int i = 6; i < 11; i++) {
      players[i] = new Player(teamName + i, i, PlayerType.BOWLER);
    }
  }

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
