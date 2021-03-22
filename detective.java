public class detective extends Player{
    public detective(String name){
        super(role.Villager,name);
    }
    public static void isMafia(Player a){
        if(a.getClass().getSimpleName().equals("godfather"))
            System.out.println("No");
        else if(a.PlayerRole ==role.Mafia)
            System.out.println("Yes");
        else System.out.println("No");
    }
}
