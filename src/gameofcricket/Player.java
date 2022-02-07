package gameofcricket;

import static java.lang.System.*;

public class Player {
    private final String playername;
    public Player(String playername){
        this.playername = playername;
    }

    public String getPlayername(){
        return playername;
    }
}
