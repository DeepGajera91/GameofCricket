package com.gameofcricket.gameofcricket;

import static java.lang.System.*;

public class ScoreCard {

  private static int scoreBoardId = 0;

  private final int matchId = ++scoreBoardId;
  private final Team team1;
  private final Team team2;
  private final int numberOfOvers;

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

  public ScoreCard(Team team1, Team team2, int numberOfOvers) {
    this.team1 = team1;
    this.team2 = team2;
    this.numberOfOvers = numberOfOvers;
  }

  public PlayerStats getPlayerStat(int team, int index) {
    return playerStats[team][index];
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

  public int getMatchId() {
    return matchId;
  }

  public Team getTeam1() {
    return team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public Player getPlayer(int team, int strikeId) {
    if (team == 0) {
      return firstBatTeam.getPlayers()[strikeId];
    } else {
      return secondBatTeam.getPlayers()[strikeId];
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

  public void printScoreCard() {
    out.printf("%nScorecard for %s VS %s Match%n", batFirstName, batSecondName);
    out.printf("%nToss Result : %s Choose to bat%n", batFirstName);

    out.printf("%nBatting Scorecard for %s%n%n", batFirstName);
    out.printf("Name\t\tR\t\tB\t\t4s\t\t6s%n");
    for (int i = 0; i < 11; i++) {
      if (playerStats[0][i].getBattingState() == BattingState.YETTOBAT) break;
      out.printf("%s\t\t", firstBatTeam.getPlayers()[i].getPlayerName());
      playerStats[0][i].printBatStat();
    }

    out.printf("%nBowling Scorecard for %s%n%n", batFirstName);
    out.printf("Name\t\tO\t\tM\t\tR\t\tW%n");
    for (int i = 6; i < 11; i++) {
      out.printf("%s\t\t", secondBatTeam.getPlayers()[i].getPlayerName());
      playerStats[1][i].printBowlStat();
    }
    out.printf(
        "%nTotal Score of %s : %d/%d (%d.%d overs)%n",
        batFirstName,
        firstBatTotalScore,
        firstBatTotalWicket,
        firstBatTotalBall / 6,
        firstBatTotalBall % 6);

    out.printf("%nBatting Scorecard for %s%n%n", batSecondName);
    out.printf("Name\t\tR\t\tB\t\t4s\t\t6s%n");
    for (int i = 0; i < 11; i++) {
      if (playerStats[1][i].getBattingState() == BattingState.YETTOBAT) break;
      out.printf("%s\t\t", secondBatTeam.getPlayers()[i].getPlayerName());
      playerStats[1][i].printBatStat();
    }

    out.printf("%nBowling Scorecard for %s%n%n", batSecondName);
    out.printf("Name\t\tO\t\tM\t\tR\t\tW%n");
    for (int i = 6; i < 11; i++) {
      out.printf("%s\t\t", firstBatTeam.getPlayers()[i].getPlayerName());
      playerStats[0][i].printBowlStat();
    }
    out.printf(
        "%nTotal Score of %s : %d/%d (%d.%d overs)%n%n",
        batSecondName,
        secondBatTotalScore,
        secondBatTotalScore,
        secondBatTotalBall / 6,
        secondBatTotalBall % 6);
    if (isDraw) {
      out.println("---> Match is Drawn%n%n");
    } else {
      out.printf("---> %s has won the Game%n%n", teamWon);
    }
  }
}
