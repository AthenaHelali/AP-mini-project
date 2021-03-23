import java.util.Scanner;

public class Day {
    Scanner scanner = new Scanner(System.in);
    public static int DayNumber = 0;

    public Day() {
        System.out.println("Day" + (++DayNumber) + "\n");
        this.WhatHappendNight();
        if(!game.gameEnd) {
            this.voting();
        }
        silencer.silenced.isSilence = false;
        silencer.silenced = null;
    }

    public boolean checkVotee(String name) {
        Player temp = game.FindPlayer(name);
        if (temp == null) {
            System.out.println("user not found\n");
            return false;
        }
        if (temp.getIsSilence()) {
            System.out.println("voter is silenced\n");
            return false;
        }
        return true;
    }

    public void voting() {
        String input = scanner.nextLine();
        while (!input.equals("end_vote")) {
            if (input.equals("get_game_state")) {
                System.out.println("Mafia = " + game.getAliveMafia() + "\n");
                System.out.println("Villager = " + game.getAliveVillagers() + "\n");
                continue;
            }
            String[] temp = scanner.nextLine().split(" ");
            if (checkVotee(temp[0])) {
                Player a = game.FindPlayer(temp[1]);
                if (a == null) {
                    System.out.println("user not found\n");
                    continue;
                } else if (!a.getIsAlive()) {
                    System.out.println("votee already dead\n");
                    continue;
                } else {
                    a.setVote();
                }
            }
        }

        String mostVotedPlayer = null;
        int vote = 0;
        int repeat = 0;
        for (int j = 0; j < game.countAlivePlayers(); j++) {
            if (game.getAlivePlayers()[j].getVote() > vote) {
                mostVotedPlayer = game.getAlivePlayers()[j].getPlayerName();
                vote = game.getAlivePlayers()[j].getVote();
            }

        }
        for (int k = 0; k < game.countAlivePlayers(); k++) {
            if (game.getAlivePlayers()[k].getVote() == vote)
                repeat++;

        }
        if (repeat < 2) {
            if (game.FindPlayer(mostVotedPlayer).getClass().getSimpleName().equals("Joker")) {
                game.winner = "Joker";
                game.gameEnd = true;
            } else {
                game.FindPlayer(mostVotedPlayer).setAlive(false);
                System.out.println(game.FindPlayer(mostVotedPlayer).getPlayerName() + "died\n");
            }
        } else {
            System.out.println("nobody died\n");
        }
        for (int i = 0; i < game.getAlivePlayers().length; i++) {
            game.getAlivePlayers()[i].resetVote();

        }
        input = scanner.nextLine();
    }

    public void WhatHappendNight() {
        if (night.mafiaTriedToKill != null) {
            System.out.println("mafia tried to kill " + night.mafiaTriedToKill.getPlayerName() + "\n");
            night.mafiaTriedToKill = null;
        }
        if (night.MafiaKilled != null) {
            System.out.println(night.MafiaKilled.getPlayerName() + " was killed\n");
            night.MafiaKilled = null;
        }
        if (silencer.silenced != null) {
            System.out.println("Silenced " + silencer.silenced.getPlayerName() + "\n");
            silencer.setCalledBefore(false);
        }
    }

}
