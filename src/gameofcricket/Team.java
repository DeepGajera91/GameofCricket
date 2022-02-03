package gameofcricket;

public class Team {
    private final String teamname;
    private Player[] players;
    public Team(String teamname){
        this.teamname = teamname;
        players = new Player[11];
    }

    public String getTeamname(){
        return teamname;
    }
}
