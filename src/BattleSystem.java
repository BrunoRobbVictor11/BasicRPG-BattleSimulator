public class BattleSystem {

    public static void randomTurn(Character player, Character enemy) {
        int randomNumber = RollSystem.rollDie(6);
        if (randomNumber % 2 == 0) {
            System.out.println(player.getName() + " attacks first!");
            TimeMethods.veryShortPause();
            MenuSystem.displayCharOptions(player, enemy);
        } else {
            System.out.println(enemy.getName() + " attacks first!");
            enemy.attack(player);
            MenuSystem.displayCharOptions(player, enemy);
        }
    }

    public static void startGame(Character player, Character enemy) {
        System.out.println(MenuSystem.randomSituation());
        TimeMethods.mediumPause();
        System.out.println(player.getName() + " looks into the enemy's face and notices: ");
        TimeMethods.mediumPause();
        MenuSystem.displayCharStats(enemy);
        TimeMethods.shortPause();
        System.out.println(" ");
        TimeMethods.loadingBar();
        System.out.println("\n" + player.getName() + " grabs the " + player.getWeapon() + " and waits...");
        TimeMethods.shortPause();
        System.out.println(enemy.getName() + " grabs the " + enemy.getWeapon() + " and waits...");
        TimeMethods.shortPause();
        TimeMethods.loadingBar();
        System.out.println("\n");
        TimeMethods.veryShortPause();
        BattleSystem.randomTurn(player, enemy);
        MenuSystem.displayCharOptions(player, enemy);
    }

}
