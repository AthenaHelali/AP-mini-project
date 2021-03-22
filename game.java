import java.util.Scanner;

public class game {
    public int Day = 0;
    public int night = 0;
    public static int numOfPlayers = 0;
    public static Player[] PlayerList = new Player[20];

    public static boolean startGame;

    public static String getInput(String input) {
        Scanner scanner = new Scanner(System.in);
        String[] SplitTest = new String[21];
        SplitTest = input.split(" ");
        if (SplitTest[0].equals("create_game"))
            return input;
        else {
            System.out.println("no game created\n");
            return getInput(scanner.nextLine());
        }
    }

    public static boolean isPlayer(String name, String[] SplitInput) {
        for (int i = 1; i < SplitInput.length; i++) {
            if (name.equals(SplitInput[i]))
                return true;
        }
        return false;
    }

    public static Player[] getAlivePlayers(Player[] PlayerList) {
        Player[] temp = new Player[countAlivePlayers(PlayerList)];
        int j = 0;
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i].isAlive) {
                temp[j] = PlayerList[i];
                j++;
            }
        }
        return temp;
    }public static Player[] getAliveVillagers(Player[] PlayerList) {
        Player[] temp = new Player[countAlivePlayers(PlayerList)];
        int j = 0;
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i].isAlive&(PlayerList[i].PlayerRole==role.Villager)) {
                temp[j] = PlayerList[i];
                j++;
            }
        }
        return temp;
    }
public static Player[] getAliveMafia(Player[] PlayerList) {
        Player[] temp = new Player[countAlivePlayers(PlayerList)];
        int j = 0;
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i].isAlive&(PlayerList[i].PlayerRole==role.Mafia)) {
                temp[j] = PlayerList[i];
                j++;
            }
        }
        return temp;
    }

    public static int countAlivePlayers(Player[] PlayerList) {
        int counter = 0;
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i].isAlive)
                counter++;
        }
        return counter;
    }

    public static boolean makeRoll(String name, String Roll, Player[] PlayerList, int i) {
        switch (Roll) {
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

    public static Player FindPlayer(String name, Player[] Playerlist) {
        for (int i = 0; i < Playerlist.length; i++) {
            if (Playerlist[i].PlayerName.equals(name))
                return Playerlist[i];
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        game a = new game();
        String input = inp.nextLine();
        String[] SplitInput = getInput(input).split(" ");
        //assign role
        for (int i = 0; i < SplitInput.length - 1; i++) {
            String[] temp = inp.nextLine().split(" ");
            if (isPlayer(temp[1], SplitInput)) {
                if (makeRoll(temp[1], temp[2], PlayerList, numOfPlayers))
                    numOfPlayers++;
                else i--;
            } else {
                System.out.println("user not found\n");
                i--;
            }

        }


    }
}

