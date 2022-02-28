package com.gameofcricket.gameofcricket.controllers.payload;

public class RequestBodyContent {
  private String team1name;
  private String team2name;
  private int numberOfOvers;

  public RequestBodyContent(String team1name, String team2name, int numberOfOvers) {
    this.team1name = team1name;
    this.team2name = team2name;
    this.numberOfOvers = numberOfOvers;
  }

  public String getTeam1name() {
    return team1name;
  }

  public void setTeam1name(String team1name) {
    this.team1name = team1name;
  }

  public String getTeam2name() {
    return team2name;
  }

  public void setTeam2name(String team2name) {
    this.team2name = team2name;
  }

  public int getNumberOfOvers() {
    return numberOfOvers;
  }

  public void setNumberOfOvers(int numberOfOvers) {
    this.numberOfOvers = numberOfOvers;
  }
}
