package gameofcricket;

import java.util.Random;

import static java.lang.System.*;

public class Match {
    private static final Random rand = new Random();
    private final Team team1;
    private final Team team2;
    private final int numberOfovers;

    private int tossWon;
    private String batFirstName;
    private String batSecondName;
    private String teamWon;
    private boolean isDraw;

    public Match(Team t1, Team t2, int  numberOfovers){
        this.team1 = t1;
        this.team2 = t2;
        this.numberOfovers = numberOfovers;
    }

    public void toss(){
        out.printf("---> %d Over Match is Starting between %s and %s%n",numberOfovers,team1.getTeamname(),team2.getTeamname());
        out.println("---> Toss time");
        //0 - team1 will bat first
        //1 - team2 will bat first

        this.tossWon = rand.nextInt(2);
        this.batFirstName = (tossWon == 0) ? team1.getTeamname() : team2.getTeamname();
        this.batSecondName = (tossWon == 0) ? team2.getTeamname() : team1.getTeamname();

        out.printf("---> %s has won the toss and decided to bat first%n",batFirstName);
    }

    private int batting(boolean isFirstBatting,int target){

        int runs = 0;
        int wickets = 0;
        int totalballs = 0;

        while(wickets < 10 && totalballs < numberOfovers*6 && (isFirstBatting || runs < target)){
            int value = rand.nextInt(8);
            if(value == 7){
                wickets++;
            }else{
                runs += value;
            }
            totalballs++;
            if(totalballs%6 == 0){
                out.printf("---> Score after %d over : %d/%d%n",totalballs/6,runs,wickets);
            }
        }
        out.printf("---> %s Total score %d/%d , %d.%d over%n%n",(isFirstBatting ? batFirstName : batSecondName),runs,wickets,totalballs/6,totalballs%6);
        return runs;
    }

    public void start(){
        int fbs = 0;
        int sbs = 0;
        int target = 0;

        out.printf("%n---> %s has started batting%n",batFirstName);

        fbs = batting(true,0);

        target = fbs+1;

        out.printf("---> %s required to score %d runs to win the match%n",batSecondName,target);

        out.printf("%n---> %s has started batting%n",batSecondName);

        sbs = batting(false,target);

        if(sbs >= target){
            this.teamWon = batSecondName;
        }else if(fbs == sbs){
            isDraw = true;
        }
        else{
            this.teamWon = batFirstName;
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
