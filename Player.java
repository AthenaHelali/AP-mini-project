public class Player {
    public role PlayerRole;
    public boolean isAlive = true;
    public String PlayerName;
    public Player nightVote;
    public boolean isSilence = false;
    public boolean isKilledToNight;
    public int vote;

    public Player(role playerRoll, String PlayerName) {
        this.PlayerRole = playerRoll;
        this.PlayerName = PlayerName;

    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setVote() {
        vote++;
    }

    public int getVote() {
        return vote;
    }

    public role getPlayerRole() {
        return PlayerRole;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public boolean getIsSilence() {
        return isSilence;
    }

    public boolean isKilledToNight() {
        return isKilledToNight;
    }

    public void resetVote() {
        vote = 0;
    }

    public Player getNightVote() {
        return nightVote;
    }

    public void setNightVote(Player nightVote) {
        this.nightVote = nightVote;
    }
}
