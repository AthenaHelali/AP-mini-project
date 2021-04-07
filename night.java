import java.util.Scanner;

public class night {
    Scanner scanner = new Scanner(System.in);
    public static int nightNumber = 0;
    public static Player MafiaKilled;
    public static Player[] mafiaTriedToKill;

    public night() {
        System.out.println("night" + (++nightNumber));
        nightPlayers();
        nightVoting();
        game.EndGame();
    }

    //print players who should wake up at night
    public void nightPlayers() {
        for (int i = 0; i < game.getAlivePlayers().length; i++) {
            switch (game.getAlivePlayers()[i].getClass().getSimpleName()) {
                case "Joker":
                    break;
                case "villager":
                    break;
                case "detective":
                    System.out.println(game.getAlivePlayers()[i].getPlayerName() + ": " + game.getAlivePlayers()[i].getClass().getSimpleName());
                    break;
                case "doctor":
                    System.out.println(game.getAlivePlayers()[i].getPlayerName() + ": " + game.getAlivePlayers()[i].getClass().getSimpleName());
                    break;
                case "bulletproof":
                    break;
                case "mafia":
                    System.out.println(game.getAlivePlayers()[i].getPlayerName() + ": " + game.getAlivePlayers()[i].getClass().getSimpleName());
                    break;
                case "godfather":
                    System.out.println(game.getAlivePlayers()[i].getPlayerName() + ": " + game.getAlivePlayers()[i].getClass().getSimpleName());
                    break;
                case "silencer":
                    System.out.println(game.getAlivePlayers()[i].getPlayerName() + ": " + game.getAlivePlayers()[i].getClass().getSimpleName());
            }
        }
    }

    public void nightVoting() {
        String inp = scanner.nextLine();
        while (!inp.equals("end_night")) {
            if (inp.equals("get_game_state")) {
                System.out.println("Mafia = " + game.getAliveMafia().length);
                System.out.println("Villager = " + game.getAliveVillagers().length);
                inp = scanner.nextLine();
                continue;
            }
            if (inp.equals("start_game")) {
                System.out.println("game has already started");
                inp = scanner.nextLine();
                continue;
            }
            String[] input = inp.split(" ");
            if (input.length < 2) {
                System.out.println("user not found");
                inp = scanner.nextLine();
                continue;
            }
            Player a = game.FindPlayer(input[0]);
            Player b = game.FindPlayer(input[1]);

            if (a == null | b == null) {
                System.out.println("user not joined");
                inp = scanner.nextLine();
                continue;
            }

            if (!a.isAlive) {
                System.out.println("user is dead");
                inp = scanner.nextLine();
                continue;
            } else {
                switch (a.getClass().getSimpleName()) {
                    case "Joker":
                        System.out.println("user can not wake up during night");
                        break;
                    case "villager":
                        System.out.println("user can not wake up during night");
                        break;
                    case "detective":
                        if (!b.getIsAlive()) {
                            System.out.println("suspect is dead");
                        } else if (detective.AlreadyAsk) {
                            System.out.println("detective has already asked");
                        } else {
                            detective.isMafia(b);
                            detective.AlreadyAsk = true;
                        }
                        break;
                    case "doctor":
                        if (checkB(b))
                            doctor.SavedByDoctor = b;
                        break;
                    case "bulletproof":
                        System.out.println("user can not wake up during night");
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
                            if (checkB(b)) {
                                silencer.setCalledBefore(true);
                                silencer.setSilence(b);
                                silencer.silenced = b;
                            }
                        }
                        break;
                    default: {
                        System.out.println("role not found");

                    }
                }
            }
            inp = scanner.nextLine();
        }


        for (int i = 0; i < game.getAliveMafia().length; i++) {
            if (game.getAliveMafia()[i].getNightVote() != null)
                game.getAliveMafia()[i].getNightVote().setVote();
        }
        int vote = 0;
        int repeat = 0;
        Player a = null;
        for (int j = 0; j < game.getAlivePlayers().length; j++) {
            if (game.getAlivePlayers()[j].getVote() > vote) {
                vote = game.getAlivePlayers()[j].getVote();
            }
        }

        Player[] equalVote = new Player[game.getAlivePlayers().length];
        int n = 0;
        for (int k = 0; k < game.getAlivePlayers().length; k++) {
            if (game.getAlivePlayers()[k].getVote() == vote) {
                equalVote[n] = game.getAlivePlayers()[k];
                repeat++;
                n++;
            }
        }
        mafiaTriedToKill=new Player[repeat];
        for (int i=0;i<repeat;i++){
           mafiaTriedToKill[i]=equalVote[i];
        }
        if (repeat == 2) {
            if (equalVote[0].equals(doctor.SavedByDoctor)) {
                MafiaKilled = equalVote[1];
                MafiaKilled.setAlive(false);
            }
            else if (equalVote[1].equals(doctor.SavedByDoctor)) {
                MafiaKilled = equalVote[0];
                MafiaKilled.setAlive(false);
            }
        }
        if (repeat == 1) {
            if (!mafiaTriedToKill[0].equals(doctor.SavedByDoctor)) {
                MafiaKilled = mafiaTriedToKill[0];
                MafiaKilled.setAlive(false);
            }
        }
        for (int k = 0; k < game.getAlivePlayers().length; k++)
            game.getAlivePlayers()[k].resetVote();
        if (MafiaKilled != null) {
            if (MafiaKilled.getClass().getSimpleName().equals("bulletproof")) {
                if (!bulletproof.AlreadyKilled) {
                    bulletproof.AlreadyKilled = true;
                    MafiaKilled.setAlive(true);
                    MafiaKilled.resetVote();
                    MafiaKilled = null;
                }
            }
        }
        ///reset Players
        doctor.SavedByDoctor = null;
        silencer.isCalledBefore = false;
        detective.AlreadyAsk=false;
    }

    public boolean checkB(Player b) {
        if (!b.getIsAlive()) {
            System.out.println("votee already dead");
            return false;
        } else return true;
    }
}
