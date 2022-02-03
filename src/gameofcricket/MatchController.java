package gameofcricket;

import java.util.Scanner;

import static java.lang.System.*;

public class MatchController {

    public static void main(String[] args){

        out.println("---> Game of Cricket between two teams");
        Scanner sc = new Scanner(System.in);

        out.println("---> Enter Team 1 name");
        String team1name = sc.nextLine();

        out.println("---> Enter Team 2 name");
        String team2name = sc.nextLine();

        Team team1 = new Team(team1name);
        Team team2 = new Team(team2name);

        out.println("Enter the number of overs");
        int  numberOfOvers = sc.nextInt();

        Match cricketMatch = new Match(team1,team2,numberOfOvers);

        cricketMatch.toss();
        cricketMatch.start();
        cricketMatch.result();
    }
}
