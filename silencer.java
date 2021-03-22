public class silencer extends Player{
    public silencer(String name){
        super(role.Mafia,name);
    }
    public static void setSilence(Player a){
        a.isSilence=true;
    }
}
