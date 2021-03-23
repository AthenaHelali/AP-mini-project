import java.util.Scanner;

public class night {
    Scanner scanner = new Scanner(System.in);
    public static int nightNumber = 0;
    public static Player MafiaKilled;
    public static Player mafiaTriedToKill;

    public night() {
        System.out.println(++nightNumber + "\n");
        PrintMafiaPlayers();
        nightVoting();


    }

    public void PrintMafiaPlayers() {
        for (int i = 0; i < game.getAliveMafia().length; i++) {
            System.out.println(game.getAliveMafia()[i].getPlayerName() + ": " + game.getAliveMafia()[i].getClass().getSimpleName() + "\n");
        }
    }

    public void nightVoting() {
        String temp = scanner.nextLine();
        while (!temp.equals("end_night\n")) {
            if(temp.equals("get_game_state")){
                System.out.println("Mafia = "+game.getAliveMafia()+"\n");
                System.out.println("Villager = "+game.getAliveVillagers()+"\n");
                continue;
            }
            String[] input = temp.split(" ");
            Player a = game.FindPlayer(input[0]);
            Player b = game.FindPlayer(input[1]);
            if (a == null | b == null) {
                System.out.println("user not found\n");
            }
            if (!a.isAlive)
                System.out.println("user is dead\n");
            else {
                switch (a.getClass().getSimpleName()) {
                    case "Joker":
                        System.out.println("user can not wake up during night\n");
                        break;
                    case "villager":
                        System.out.println("user can not wake up during night\n");
                        break;
                    case "detective":
                        if (!checkB(b)) {
                            System.out.println("suspect is dead\n");
                        } else if (detective.AlreadyAsk) {
                            System.out.println("detective has already asked\n");
                        } else
                            detective.isMafia(b);
                        break;
                    case "doctor":
                        doctor.SavedByDoctor = b;
                        break;
                    case "bulletproof":
                        System.out.println("user can not wake up during night\n");
                        break;
                    case "mafia":
                        if (checkB(b))
                            a.setNightVote(b);
                        break;
                    case "godfather":
                        if (checkB(b))
                            a.setNightVote(b);
                        break;
                    case "silencer":
                        if (silencer.isCalledBefore) {
                            if (checkB(b))
                                a.setNightVote(b);
                        } else {
                            silencer.isCalledBefore = true;
                            silencer.setSilence(b);
                        }
                        break;
                    default: {
                        System.out.println("role not found\n");

                    }
                }
            }
            temp = scanner.nextLine();
        }
        for (int i = 0; i < game.getAliveMafia().length; i++) {
            game.getAliveMafia()[i].getNightVote().setVote();
        }
        int vote = 0;
        int repeat = 0;
        Player a;
        for (int j = 0; j < game.getAliveMafia().length; j++) {
            if (game.getAliveMafia()[j].getVote() > vote) {
                a = game.getAliveMafia()[j];
                vote = game.getAlivePlayers()[j].getVote();
            }
        }

        Player[] equalvote = new Player[game.getAlivePlayers().length];
        int n = 0;
        for (int k = 0; k < game.getAliveMafia().length; k++) {
            if (game.getAliveMafia()[k].getVote() == vote) {
                equalvote[n] = game.getAliveMafia()[k];
                repeat++;
                n++;

            }
        }
        if (repeat == 2) {
            if (equalvote[0].equals(doctor.SavedByDoctor))
                MafiaKilled = equalvote[1];
            else if (equalvote[1].equals(doctor.SavedByDoctor))
                MafiaKilled = equalvote[1];
        }
        if (repeat == 1) {
            mafiaTriedToKill = equalvote[0];
            if (!mafiaTriedToKill.equals(doctor.SavedByDoctor))
                MafiaKilled = mafiaTriedToKill;
        }
        for (int k = 0; k < game.getAlivePlayers().length; k++)
            game.getAlivePlayers()[k].resetVote();
        if (MafiaKilled.getClass().getSimpleName().equals("bulletproof")) {
            if (!bulletproof.AlreadyKilled) {
                bulletproof.AlreadyKilled = true;
                MafiaKilled = null;
            }
        }
    }

    public boolean checkB(Player b) {
        if (!b.getIsAlive()) {
            System.out.println("votee already dead");
            return false;
        } else return true;
    }
}
