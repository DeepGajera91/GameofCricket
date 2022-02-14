package com.gameofcricket.gameofcricket;

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
    return rand.nextInt(8);
  }

  private int batting(int team, int target) {

    int runs = 0;
    int wickets = 0;
    int totalballs = 0;
    int[] lastSixballs = new int[6];
    int index = 0;
    int strikeId = index++;
    int nonStrikeId = index++;
    int bowlerId = 6;

    scoreCard.getPlayerStat(team, strikeId).setBattingState(BattingState.NOTOUT);
    scoreCard.getPlayerStat(team, nonStrikeId).setBattingState(BattingState.NOTOUT);

    while (wickets < 10
        && totalballs < scoreCard.getNumberOfOvers() * 6
        && (team == 0 || runs < target)) {
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
      if (totalballs % 6 == 0) {
        out.printf("---> Score after %d over : %d/%d%n", totalballs / 6, runs, wickets);
        bowlerId = nextBowler(bowlerId);
        int temp = nonStrikeId;
        nonStrikeId = strikeId;
        strikeId = temp;
      }
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
    int first_batting_score = 0;
    int second_batting_score = 0;
    int target = 0;

    out.printf("%n---> %s has started batting%n", scoreCard.getBatFirstName());

    first_batting_score = batting(0, 0);

    target = first_batting_score + 1;

    out.printf(
        "---> %s required to score %d runs to win the match%n",
        scoreCard.getBatSecondName(), target);

    out.printf("%n---> %s has started batting%n", scoreCard.getBatSecondName());

    second_batting_score = batting(1, target);

    if (second_batting_score >= target) {
      scoreCard.setTeamWon(scoreCard.getBatSecondName());
    } else if (first_batting_score == second_batting_score) {
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
