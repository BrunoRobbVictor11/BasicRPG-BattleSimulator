public class Main {
    public static void main(String[] args) {
        TimeMethods.loadingBar();
        System.out.println("\n");
        Character player = MenuSystem.displayMenu();
        player.chooseWeapon();
        MenuSystem.displayCharStats(player);
        TimeMethods.shortPause();
        System.out.println(" ");
        MenuSystem.gameMessage();
        TimeMethods.waitForEnter();
        Character enemy = MenuSystem.createRandomEnemy();
        BattleSystem.startGame(player, enemy);
        }
    }
