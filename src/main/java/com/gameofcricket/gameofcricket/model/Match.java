package com.gameofcricket.gameofcricket.model;

import java.util.Random;

import static java.lang.System.*;

public class Match {
  private static final Random rand = new Random();
  private final ScoreCard scoreCard;

  public Match(Team team1, Team team2, int numberOfOvers) {
    this.scoreCard = new ScoreCard(team1, team2, numberOfOvers);
  }

  public ScoreCard getScoreCard() {
    return scoreCard;
  }

  public void toss() {
    out.printf(
        "---> %d Over Match is Starting between %s and %s%n%n",
        scoreCard.getNumberOfOvers(),
        scoreCard.getTeam1().getTeamname(),
        scoreCard.getTeam2().getTeamname());
    out.println("---> Toss time");
    // 0 - team1 will bat first
    // 1 - team2 will bat first

    scoreCard.setTossWon(rand.nextInt(2));
    out.printf("---> %s has won the toss and decided to bat first%n", scoreCard.getBatFirstName());
  }

  private int nextBowler(int bowlerId) {
    bowlerId++;
    if (bowlerId == 11) {
      bowlerId = 6;
    }
    return bowlerId;
  }

  private int ScoreGenerator(int team, int strikeId) {

    int playerRating = scoreCard.getPlayer(team, strikeId).getRating();
    playerRating/=10;

    int outcome = rand.nextInt(100);

    if (outcome >= 0 && outcome <= 10) return 0;
    if (outcome >= 11 && outcome <= 40) return 1;
    if (outcome >= 41 && outcome <= 60) return 2;
    if (outcome >= 61 && outcome <= 70) return 3;
    if (outcome >= 71 && outcome <= 80) return 4;
    if (outcome >= 81 && outcome <= 85) return 5;
    if (outcome >= 86 && outcome <= 86+playerRating) return 6;

    return 7;
  }

  private int batting(int team, int target) {

    int runs = 0;
    int wickets = 0;
    int totalballs = 0;
    int lastSixBallsRuns = 0;
    int index = 0;
    int strikeId = index++;
    int nonStrikeId = index++;
    int bowlerId = 6;

    scoreCard.getPlayerStat(team, strikeId).setBattingState(BattingState.NOTOUT);
    scoreCard.getPlayerStat(team, nonStrikeId).setBattingState(BattingState.NOTOUT);

    while (wickets < 10
        && totalballs < scoreCard.getNumberOfOvers() * 6
        && (team == 0 || runs < target)) {
      int i = 0;
      lastSixBallsRuns = 0;
      for (;
          i < 6
              && wickets < 10
              && totalballs < scoreCard.getNumberOfOvers() * 6
              && (team == 0 || runs < target);
          i++) {
        int value = ScoreGenerator(team, strikeId);
        if (value == 7) {
          wickets++;
          scoreCard.getPlayerStat(team, strikeId).setPlayerOut();
          scoreCard.getPlayerStat((1 - team), bowlerId).addWicket();
          strikeId = index++;
          if (strikeId <= 10)
            scoreCard.getPlayerStat(team, strikeId).setBattingState(BattingState.NOTOUT);
        } else {
          runs += value;
          lastSixBallsRuns += value;
          scoreCard
              .getPlayerStat(team, strikeId)
              .addScoredRuns(value, 1, (value == 4) ? 1 : 0, (value == 6) ? 1 : 0);
          scoreCard.getPlayerStat((1 - team), bowlerId).addRunsGiven(value, 1);
          if (value == 1 || value == 3) {
            int temp = nonStrikeId;
            nonStrikeId = strikeId;
            strikeId = temp;
          }
        }
        totalballs++;
        scoreCard.setTotalWicket(wickets, team);
        scoreCard.setTotalBall(totalballs, team);
        scoreCard.setTotalScore(runs, team);
      }
      if (i == 6 && lastSixBallsRuns == 0) {
        scoreCard.getPlayerStat((1 - team), bowlerId).addMaidenOvers();
      }
      out.printf(
          "---> Score after %d.%d over : %d/%d%n", totalballs / 6, totalballs % 6, runs, wickets);
      bowlerId = nextBowler(bowlerId);
      int temp = nonStrikeId;
      nonStrikeId = strikeId;
      strikeId = temp;
    }

    out.printf(
        "---> %s Total score %d/%d , %d.%d over%n%n",
        (team == 0 ? scoreCard.getBatFirstName() : scoreCard.getBatSecondName()),
        runs,
        wickets,
        totalballs / 6,
        totalballs % 6);
    return runs;
  }

  public void start() {
    int firstBattingScore = 0;
    int secondBattingScore = 0;
    int target = 0;

    out.printf("%n---> %s has started batting%n", scoreCard.getBatFirstName());

    firstBattingScore = batting(0, 0);

    target = firstBattingScore + 1;

    out.printf(
        "---> %s required to score %d runs to win the match%n",
        scoreCard.getBatSecondName(), target);

    out.printf("%n---> %s has started batting%n", scoreCard.getBatSecondName());

    secondBattingScore = batting(1, target);

    if (secondBattingScore >= target) {
      scoreCard.setTeamWon(scoreCard.getBatSecondName());
    } else if (firstBattingScore == secondBattingScore) {
      scoreCard.setDraw(true);
    } else {
      scoreCard.setTeamWon(scoreCard.getBatFirstName());
    }
  }

  public void result() {
    if (scoreCard.isDraw()) {
      out.println("---> Match is Drawn");
    } else {
      out.printf("---> %s has won the Game%n", scoreCard.getTeamWon());
    }
  }
}
