import java.util.Scanner;

public class game {

    public int Day = 0;
    public int night = 0;
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
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i].isAlive) {
                temp[j] = PlayerList[i];
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
        for (int j = 0; j < game.PlayerList.length; j++) {
            if (PlayerList[j].getPlayerRole() == role.Mafia & PlayerList[j].isAlive)
                countAliveMafia++;
        }
        temp = new Player[countAliveMafia];
        int k = 0;
        for (int i = 0; i < game.PlayerList.length; i++) {
            if (PlayerList[i].isAlive & (PlayerList[i].PlayerRole == role.Mafia)) {
                temp[k] = PlayerList[i];
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
            case "Joker":
                PlayerList[i] = new Joker(name);
                break;
            case "villager":
                PlayerList[i] = new villager(name);
                break;
            case "detective":
                PlayerList[i] = new detective(name);
                break;
            case "doctor":
                PlayerList[i] = new doctor(name);
                break;
            case "bulletproof":
                PlayerList[i] = new bulletproof(name);
                break;
            case "mafia":
                PlayerList[i] = new mafia(name);
                break;
            case "godfather":
                PlayerList[i] = new godfather(name);
                break;
            case "silencer":
                PlayerList[i] = new silencer(name);
                break;
            default: {
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
        if (getAliveMafia()[0] == null) {
            EndGame = true;
            winner = "Villagers won!";
        }
        if (getAliveVillagers().length < getAliveMafia().length) {
            EndGame = true;
            winner = "Mafia won!";
        }
    }

    //check start
    public static boolean checkStart(String getEntry) {
        boolean check = true;
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i] == null)
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
                System.out.println("user not found\n");
                input=inp.nextLine();
                continue;
            } else {
                if (isPlayer(temp[1], SplitInput) & (game.FindPlayer(temp[1]) == null)) {
                    if (makeRole(temp[1], temp[2], PlayerList, numOfPlayers))
                        numOfPlayers++;
                } else
                    System.out.println("user not found\n");

            }
            getEntry = inp.nextLine();
        }
        for (int i = 0; i < PlayerList.length; i++) {
            System.out.println(PlayerList[i].getPlayerName() + " : " + PlayerList[i].getClass().getSimpleName() + "\n");
        }
        System.out.println("Ready? Set! Go.\n");

        while (!EndGame) {
            Day temp = new Day();

        }
        System.out.println(winner);
    }
}

