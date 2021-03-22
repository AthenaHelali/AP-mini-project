import java.util.Scanner;

public class night {
    Scanner scanner=new Scanner(System.in);
    public static int nightNumber=0;
    public night(){
        System.out.println(++nightNumber+"\n");
        PrintMafiaPlayers();


    }
    public void PrintMafiaPlayers(){
        for (int i=0;i<game.getAliveMafia().length;i++){
            System.out.println(game.getAliveMafia()[i].getPlayerName()+": "+game.getAliveMafia()[i].getClass().getSimpleName()+"\n");
        }
    }
    public void nightVoting(){
        String temp=scanner.nextLine();
        while (!temp.equals("end_night\n")){
            String[] input=temp.split(" ");
            Player a=game.FindPlayer(input[0]);
            Player b=game.FindPlayer(input[1]);
            Player SavedByDoctor;
            if(!a.isAlive)
                System.out.println("user is dead\n");
            else {
                switch (a.getClass().getSimpleName()){
                    case "Joker":
                        System.out.println("user can not wake up during night\n");
                        break;
                    case "villager":
                        System.out.println("user can not wake up during night\n");
                        break;
                    case "detective":
                        detective.isMafia(b);
                        break;
                    case "doctor":
                        SavedByDoctor=b;
                        break;
                    case "bulletproof":
                        System.out.println("user can not wake up during night\n");
                        break;
                    case "mafia":
                        b.setVote();
                        break;
                    case "godfather":
                        b.setVote();
                        break;
                    case "silencer":
                        if(silencer.isCalledBefore)
                            b.setVote();
                        else
                            silencer.setSilence(b);

                        break;
                    default: {
                        System.out.println("role not found\n");

                    }
                }
            }
            temp=scanner.nextLine();
        }
    }
    public void checkB(Player b){
        if(!b.getIsAlive())
            System.out.println("votee already dead");
    }
}
