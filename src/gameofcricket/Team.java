package gameofcricket;

import java.util.Locale;

public class Team {
    private final String teamname;
    private Player[] players = new Player[11];

    public Team(String teamname){
        this.teamname = teamname.toUpperCase(Locale.ROOT);

        for(int i=0;i<11;i++) {
            players[i] = new Player(teamname+i);
        }
    }

    public String getTeamname(){
        return teamname;
    }
}
