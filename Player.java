public class Player {
    public role PlayerRole;
    public boolean isAlive;
    public String PlayerName;
    public boolean isSilence;
    public boolean isKilledToNight;
    public int votee;


    public Player(role playerRoll,String PlayerName) {
        this.PlayerRole = playerRoll;
        this.PlayerName=PlayerName;

    }
    public void setVote(){
        votee++;
    }
    public void resetVote(){
        votee=0;
    }

}
