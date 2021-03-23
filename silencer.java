public class silencer extends Player{
    public static boolean isCalledBefore=false;
    public static Player silenced;
    public silencer(String name){
        super(role.Mafia,name);
    }
    public static void setSilence(Player a){
        a.isSilence=true;
        silenced=a;
    }

    public static void setCalledBefore(boolean calledBefore) {
        isCalledBefore = calledBefore;
    }

    public boolean isCalledBefore() {
        return isCalledBefore;
    }
}
