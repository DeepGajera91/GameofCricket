package com.gameofcricket.gameofcricket;

public class PlayerStats {
  private final int id;
  private int noOfRunsScored = 0;
  private int noOfBallsPlayed = 0;
  private int noOfFours = 0;
  private int noOfSixes = 0;
  private int noOfOvers = 0;
  private int noOfBallsBowled = 0;
  private int noOfRunsGiven = 0;
  private int noOfWickets = 0;
  private int noOfMaidenOvers = 0;
  private BattingState battingState = BattingState.YETTOBAT;

  public PlayerStats(int id) {
    this.id = id;
  }

  public int getNoOfRunsScored() {
    return noOfRunsScored;
  }

  public int getNoOfBallsPlayed() {
    return noOfBallsPlayed;
  }

  public int getNoOfFours() {
    return noOfFours;
  }

  public int getNoOfSixes() {
    return noOfSixes;
  }

  public int getNoOfOvers() {
    return noOfOvers;
  }

  public int getNoOfRunsGiven() {
    return noOfRunsGiven;
  }

  public int getNoOfMaidenOvers() {
    return noOfMaidenOvers;
  }

  public BattingState getBattingState() {
    return battingState;
  }

  public void setBattingState(BattingState battingState) {
    this.battingState = battingState;
  }

  public void addScoredRuns(int runsScored, int ballsPlayed, int noOfFours, int noOfSixes) {
    this.noOfRunsScored += runsScored;
    this.noOfBallsPlayed += ballsPlayed;
    this.noOfFours += noOfFours;
    this.noOfSixes += noOfSixes;
  }

  public void addRunsGiven(int runsGiven, int noOfBallsBowled) {
    this.noOfRunsGiven += runsGiven;
    this.noOfBallsBowled += noOfBallsBowled;
    if (this.noOfBallsBowled % 6 == 0) {
      this.noOfOvers++;
    }
  }

  public void setPlayerOut() {
    this.noOfBallsPlayed++;
    this.battingState = BattingState.OUT;
  }

  public void addWicket() {
    this.noOfWickets++;
    this.noOfBallsBowled++;
    if (this.noOfBallsBowled % 6 == 0) {
      this.noOfOvers++;
    }
  }

  public void addMaidenOvers(int noOfMaidenOvers) {
    this.noOfMaidenOvers += noOfMaidenOvers;
  }

  public void printBatStat() {
    System.out.printf(
        "%d\t\t%d\t\t%d\t\t%d%n", noOfRunsScored, noOfBallsPlayed, noOfFours, noOfSixes);
  }

  public void printBowlStat() {
    System.out.printf(
        "%d\t\t%d\t\t%d\t\t%d%n", noOfOvers, noOfMaidenOvers, noOfRunsGiven, noOfWickets);
  }
}
