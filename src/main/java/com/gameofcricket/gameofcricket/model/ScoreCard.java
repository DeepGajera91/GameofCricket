package com.gameofcricket.gameofcricket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ScoreCard {

  @Id private String id;
  private Team team1;
  private Team team2;
  private int numberOfOvers;

  private int winRunMargin;
  private int winWicketMargin;
  private int tossWon;
  private Team firstBatTeam;
  private Team secondBatTeam;
  private String batFirstName;
  private String batSecondName;
  private String teamWon;
  private String result;
  private boolean isDraw;

  private int firstBatTotalScore = 0;
  private int firstBatTotalBall = 0;
  private int firstBatTotalWicket = 0;
  private int secondBatTotalScore = 0;
  private int secondBatTotalBall = 0;
  private int secondBatTotalWicket = 0;

  private PlayerStats[][] playerStats =
      new PlayerStats[2][11]; // 2D array, 0 - for first batting team, 1 - for second batting team

  public ScoreCard() {}

  public ScoreCard(Team team1, Team team2, int numberOfOvers) {
    this.team1 = team1;
    this.team2 = team2;
    this.numberOfOvers = numberOfOvers;
  }

  public void setPlayerBattingState(int team, int index, BattingState battingState) {
    playerStats[team][index].setBattingState(battingState);
  }

  public void setPlayerOut(int team, int index) {
    playerStats[team][index].setPlayerOut();
  }

  public void addWicketToBowler(int team, int index) {
    playerStats[team][index].addWicket();
  }

  public void addScoredRuns(
      int team, int index, int runsScored, int ballsPlayed, int noOfFours, int noOfSixes) {
    playerStats[team][index].addScoredRuns(runsScored, ballsPlayed, noOfFours, noOfSixes);
  }

  public void addRunsGiven(int team, int index, int runsGiven, int noOfBallsBowled) {
    playerStats[team][index].addRunsGiven(runsGiven, noOfBallsBowled);
  }

  public void addMaidenOvers(int team, int index) {
    playerStats[team][index].addMaidenOvers();
  }

  public int getFirstBatTotalScore() {
    return firstBatTotalScore;
  }

  public int getFirstBatTotalBall() {
    return firstBatTotalBall;
  }

  public int getFirstBatTotalWicket() {
    return firstBatTotalWicket;
  }

  public int getSecondBatTotalScore() {
    return secondBatTotalScore;
  }

  public int getSecondBatTotalBall() {
    return secondBatTotalBall;
  }

  public int getSecondBatTotalWicket() {
    return secondBatTotalWicket;
  }

  public String getMatchId() {
    return id;
  }

  public Team getTeam1() {
    return team1;
  }

  public String getTeam1Name() {
    return team1.getTeamname();
  }

  public Team getTeam2() {
    return team2;
  }

  public String getTeam2Name() {
    return team2.getTeamname();
  }

  public int getPlayerRating(int team, int strikeId) {
    if (team == 0) {
      return firstBatTeam.getPlayers()[strikeId].getRating();
    } else {
      return secondBatTeam.getPlayers()[strikeId].getRating();
    }
  }

  public int getNumberOfOvers() {
    return numberOfOvers;
  }

  public String getResult() {
    return result;
  }

  public int getWinRunMargin() {
    return winRunMargin;
  }

  public int getWinWicketMargin() {
    return winWicketMargin;
  }

  public int getTossWon() {
    return tossWon;
  }

  public String getBatFirstName() {
    return batFirstName;
  }

  public String getBatSecondName() {
    return batSecondName;
  }

  public String getTeamWon() {
    return teamWon;
  }

  public boolean isDraw() {
    return isDraw;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setWinRunMargin(int winRunMargin) {
    this.winRunMargin = winRunMargin;
  }

  public void setWinWicketMargin(int winWicketMargin) {
    this.winWicketMargin = winWicketMargin;
  }

  public void setBatFirstName(String batFirstName) {
    this.batFirstName = batFirstName;
  }

  public void setBatSecondName(String batSecondName) {
    this.batSecondName = batSecondName;
  }

  public void setTossWon(int tossWon) {
    this.tossWon = tossWon;
    this.firstBatTeam = (tossWon == 0) ? this.getTeam1() : this.getTeam2();
    this.secondBatTeam = (tossWon == 0) ? this.getTeam2() : this.getTeam1();
    this.batFirstName = this.firstBatTeam.getTeamname();
    this.batSecondName = this.secondBatTeam.getTeamname();

    for (int i = 0; i < 11; i++) {
      this.playerStats[0][i] = new PlayerStats(this.firstBatTeam.getPlayers()[i].getPlayerID());
      this.playerStats[1][i] = new PlayerStats(this.secondBatTeam.getPlayers()[i].getPlayerID());
    }
  }

  public void setTeamWon(String teamWon) {
    this.teamWon = teamWon;
  }

  public void setDraw(boolean draw) {
    isDraw = draw;
  }

  public void setTotalScore(int totalScore, int team) {
    if (team == 0) {
      this.firstBatTotalScore = totalScore;
    } else {
      this.secondBatTotalScore = totalScore;
    }
  }

  public void setTotalBall(int totalBall, int team) {
    if (team == 0) {
      this.firstBatTotalBall = totalBall;
    } else {
      this.secondBatTotalBall = totalBall;
    }
  }

  public void setTotalWicket(int totalWicket, int team) {
    if (team == 0) {
      this.firstBatTotalWicket = totalWicket;
    } else {
      this.secondBatTotalWicket = totalWicket;
    }
  }
}
