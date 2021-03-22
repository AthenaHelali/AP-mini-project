public class silencer extends Player{
    public static boolean isCalledBefore=false;
    public silencer(String name){
        super(role.Mafia,name);
    }
    public static void setSilence(Player a){
        a.isSilence=true;
    }

    public void setCalledBefore(boolean calledBefore) {
        isCalledBefore = calledBefore;
    }

    public boolean isCalledBefore() {
        return isCalledBefore;
    }
}
