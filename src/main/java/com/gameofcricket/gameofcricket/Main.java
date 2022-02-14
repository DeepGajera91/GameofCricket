package com.gameofcricket.gameofcricket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {

  private static List<ScoreCard> scorecards = new ArrayList<>();

  public static void main(String[] args) {
    while (true) {
      out.println("Click n to start the new match and Click x to terminate the program");
      Scanner sc = new Scanner(System.in);
      char ch = sc.next().charAt(0);
      if (ch == 'n') {
        ScoreCard currentScorecard = MatchController.matchcontrollerFunc();
        currentScorecard.printScoreCard();
        scorecards.add(currentScorecard);
      } else {
        break;
      }
    }
  }
}
