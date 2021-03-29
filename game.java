import java.util.Scanner;

public class game {
    public static int numOfPlayers = 0;
    public static Player[] PlayerList;
    public static boolean EndGame = false;
    public static String winner;

    public static String getPlayersName(String input) {
        Scanner scanner = new Scanner(System.in);
        String[] SplitTest = input.split(" ");
        if (SplitTest[0].equals("create_game"))
            return input;
        else {
            System.out.println("no game created\n");
            return getPlayersName(scanner.nextLine());
        }
    }

    ///find Player by its name
    public static boolean isPlayer(String name, String[] SplitInput) {
        for (int i = 1; i < SplitInput.length; i++) {
            if (name.equals(SplitInput[i]))
                return true;
        }
        return false;
    }

    ///return Array of alive players
    public static Player[] getAlivePlayers() {
        Player[] temp = new Player[countAlivePlayers()];
        int j = 0;
        for (Player player : PlayerList) {
            if (player.isAlive) {
                temp[j] = player;
                j++;
            }
        }
        return temp;
    }

    ///get array of alive villagers
    public static Player[] getAliveVillagers() {
        Player[] temp;
        int counter = 0;
        int k = 0;
        for (int j = 0; j < PlayerList.length; j++) {
            if (game.PlayerList[j].isAlive & (game.PlayerList[j].PlayerRole == role.Villager)) {
                counter++;
            }
        }
        temp = new Player[counter];
        for (int i = 0; i < PlayerList.length; i++) {
            if (game.PlayerList[i].isAlive & (game.PlayerList[i].PlayerRole == role.Villager)) {
                temp[k] = PlayerList[i];
                k++;
            }
        }
        return temp;
    }

    //get array of alive mafia
    public static Player[] getAliveMafia() {
        Player[] temp;
        int countAliveMafia = 0;
        for (Player player : game.PlayerList) {
            if (player.getPlayerRole() == role.Mafia & player.isAlive)
                countAliveMafia++;
        }
        temp = new Player[countAliveMafia];
        int k = 0;
        for (Player player : game.PlayerList) {
            if (player.isAlive & (player.PlayerRole == role.Mafia)) {
                temp[k] = player;
                k++;
            }
        }
        return temp;
    }

    public static int countAlivePlayers() {
        int counter = 0;
        for (int i = 0; i < game.PlayerList.length; i++) {
            if (game.PlayerList[i].isAlive)
                counter++;
        }
        return counter;
    }

    //make players by their role
    public static boolean makeRole(String name, String role, Player[] PlayerList, int i) {
        switch (role) {
            case "Joker" -> PlayerList[i] = new Joker(name);
            case "villager" -> PlayerList[i] = new villager(name);
            case "detective" -> PlayerList[i] = new detective(name);
            case "doctor" -> PlayerList[i] = new doctor(name);
            case "bulletproof" -> PlayerList[i] = new bulletproof(name);
            case "mafia" -> PlayerList[i] = new mafia(name);
            case "godfather" -> PlayerList[i] = new godfather(name);
            case "silencer" -> PlayerList[i] = new silencer(name);
            default -> {
                System.out.println("role not found\n");
                return false;
            }
        }
        return true;
    }

    //find player by its name
    public static Player FindPlayer(String name) {
        for (int i = 0; i < game.PlayerList.length; i++) {
            if (PlayerList[i] != null) {
                if (game.PlayerList[i].PlayerName.equals(name))
                    return game.PlayerList[i];
            }
        }
        return null;
    }

    //check if the game is ended
    public static void EndGame() {
        if (getAliveMafia().length == 0) {
            EndGame = true;
            winner = "Villagers won!";
        }
        if (getAliveVillagers().length <= getAliveMafia().length) {
            EndGame = true;
            winner = "Mafia won!";
        }
    }

    //check start
    public static boolean checkStart(String getEntry) {
        boolean check = true;
        for (Player player : PlayerList) {
            if (player == null)
                check = false;
        }
        if (getEntry.equals("start_game")) {
            if (!check) {
                System.out.println("one or more player do not have a role");
                return false;
            }
            return true;
        } else
            return false;

    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String input = inp.nextLine();
        String[] SplitInput = getPlayersName(input).split(" ");
        //assign role
        PlayerList = new Player[SplitInput.length - 1];
        String getEntry = inp.nextLine();
        while (!checkStart(getEntry)) {
            String[] temp = getEntry.split(" ");
            if (temp.length < 3) {
                if (temp[0].equals("start_game")) {
                    getEntry = inp.nextLine();
                    continue;
                } else {
                    System.out.println("user not found\n");
                    getEntry = inp.nextLine();
                    continue;
                }
            } else if (!temp[0].equals("assign_role")) {
                System.out.println("invalid input\n");
                getEntry = inp.nextLine();
                continue;
            } else if (game.FindPlayer(temp[1]) == null) {
                if (isPlayer(temp[1], SplitInput)) {
                    if (makeRole(temp[1], temp[2], PlayerList, numOfPlayers))
                        numOfPlayers++;
                } else
                    System.out.println("user not found\n");
            } else System.out.println("role already assigned");
            getEntry = inp.nextLine();
        }
        for (Player player : PlayerList) {
            System.out.println(player.getPlayerName() + " : " + player.getClass().getSimpleName() + "\n");
        }
        System.out.println("Ready? Set! Go.\n");

        while (!EndGame) {
            Day temp = new Day();
        }
        System.out.println(winner);
    }
}

