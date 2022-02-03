package gameofcricket;

import java.util.Random;

import static java.lang.System.*;

public class Match {
    private static final Random rand = new Random();
    private final Team team1;
    private final Team team2;
    private int tossWon;
    private String batFirstName;
    private String batSecondName;
    private String teamWon;
    private boolean isDraw;
    private final int numberOfovers;

    public Match(Team t1, Team t2, int  numberOfovers){
        this.team1 = t1;
        this.team2 = t2;
        this.numberOfovers = numberOfovers;
    }

    public void toss(){
        out.printf("---> Match Starting between %s and %s%n",team1.getTeamname(),team2.getTeamname());
        out.println("---> Toss time");
        //0 - team1 will bat first
        //1 - team2 will bat first

        this.tossWon = rand.nextInt(2);

        if(tossWon == 0){
            batFirstName = team1.getTeamname();
            batSecondName = team2.getTeamname();
        }else{
            batFirstName = team2.getTeamname();
            batSecondName = team1.getTeamname();
        }

        out.printf("---> %s has won the toss and decided to bat first%n",batFirstName);
        out.printf("---> %s started batting%n",batFirstName);
    }

    public void start(){
        int fbs = 0;
        int fbw = 0;
        int sbs = 0;
        int sbw = 0;
        int target = 0;
        int totalballs = 0;
        while(fbw < 10 && totalballs < numberOfovers*6){
            int value = rand.nextInt(8);
            if(value == 7){
                fbw++;
            }else{
                fbs += value;
            }
            totalballs++;
        }
        target = fbs+1;

        out.printf("---> %s scored %d runs%n",batFirstName,fbs);
        out.printf("---> %s required to score %d runs to win the match%n",batSecondName,target);
        out.printf("---> %s started batting%n",batSecondName);

        totalballs = 0;

        while(sbs < target && sbw < 10 && totalballs < numberOfovers*6){
            int value = rand.nextInt(8);
            if(value == 7){
                sbw++;
            }else{
                sbs += value;
            }
            totalballs++;
        }

        out.printf("---> %s scored %d runs%n",batSecondName,sbs);

        if(sbs >= target){
            if(this.tossWon == 0){
                this.teamWon = team2.getTeamname();
            }else{
                this.teamWon = team1.getTeamname();
            }
        }else if(fbs == sbs){
            isDraw = true;
        }
        else{
            if(this.tossWon == 0){
                this.teamWon = team1.getTeamname();
            }else{
                this.teamWon = team2.getTeamname();
            }
        }
    }

    public void result(){
        if(isDraw){
            out.println("---> Match is Drawn");
        }
        else{
            out.printf("---> %s has won the Game%n",teamWon);
        }
    }
}
