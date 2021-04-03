import java.util.Scanner;

public class Day {
    Scanner scanner = new Scanner(System.in);
    public static int DayNumber = 0;

    public Day() {
        System.out.println("Day" + (++DayNumber) + "\n");
        this.WhatHappendNight();
        this.voting();
        if (silencer.silenced != null) {
            silencer.silenced.isSilence = false;
            silencer.silenced=null;
        }
        game.EndGame();
        if (!game.EndGame) {
            night a = new night();
        }
    }

    public boolean checkVotee(String name) {
        Player temp = game.FindPlayer(name);
        if (temp == null) {
            System.out.println("user not found\n");
            return false;
        }
        if(!temp.getIsAlive()){
            System.out.println("voter is dead\n");
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
                System.out.println("Mafia = " + game.getAliveMafia().length + "\n");
                System.out.println("Villager = " + game.getAliveVillagers().length + "\n");
                input=scanner.nextLine();
                continue;
            }
            if(input.equals("start_game")){
                System.out.println("game has already started");
                input=scanner.nextLine();
                continue;
            }
            String[] temp = input.split(" ");
            if(!checkVotee(temp[0])){
                input=scanner.nextLine();
                continue;
            }
            if (temp.length > 1) {
                Player a = game.FindPlayer(temp[1]);
                if (a == null) {
                    System.out.println("user not found\n");
                    input = scanner.nextLine();
                    continue;
                } else if (!a.getIsAlive()) {
                    System.out.println("votee already dead\n");
                    input = scanner.nextLine();
                    continue;
                } else {
                    a.setVote();
                }
            }
            else{
                System.out.println("user not found\n");
            }
            input = scanner.nextLine();
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
                game.winner = "Joker won!";
                game.EndGame = true;
            } else {
                game.FindPlayer(mostVotedPlayer).setAlive(false);
                System.out.println(game.FindPlayer(mostVotedPlayer).getPlayerName() + " died\n");
            }
        } else {
            System.out.println("nobody died\n");
        }
        for (int i = 0; i < game.getAlivePlayers().length; i++) {
            game.getAlivePlayers()[i].resetVote();
        }
    }

    public void WhatHappendNight() {
        if (night.mafiaTriedToKill!=null) {
            for (int i=0;i<night.mafiaTriedToKill.length;i++) {
                if(!night.mafiaTriedToKill[i].getClass().getSimpleName().equals("bulletproof"))
                System.out.println("mafia tried to kill " + night.mafiaTriedToKill[i].getPlayerName() + "\n");
            }
        }
        night.mafiaTriedToKill=null;
        if (night.MafiaKilled != null) {
            System.out.println(night.MafiaKilled.getPlayerName() + " was killed\n");
            night.MafiaKilled = null;
        }
        if (silencer.silenced != null) {
            System.out.println("Silenced " + silencer.silenced.getPlayerName() + "\n");
        }
    }
}
