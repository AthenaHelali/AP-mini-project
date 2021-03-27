public class silencer extends Player{
    public static boolean isCalledBefore=false;
    public static Player silenced=null;
    public silencer(String name){
        super(role.Mafia,name);
    }
    public static void setSilence(Player a){
        a.isSilence=true;
    }

    public static void setCalledBefore(boolean calledBefore) {
        isCalledBefore = calledBefore;
    }

    public boolean isCalledBefore() {
        return isCalledBefore;
    }
}
