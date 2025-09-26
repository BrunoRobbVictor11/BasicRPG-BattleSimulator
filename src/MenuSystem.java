import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuSystem {
    public static String name;
    public static Scanner scanner = new Scanner(System.in);
    public static int count = 0;

    public static void displayCharOptions(Character charMain, Character enemy) {
        int choice;
        do {
            TimeMethods.shortPause();
            System.out.println("\n+======STATUS======+");
            TimeMethods.veryShortPause();
            System.out.printf("|    HEALTH: %-3d   |%n", charMain.getHealth());
            TimeMethods.veryShortPause();
            System.out.println("+======++++++======+");
            TimeMethods.shortPause();
            System.out.println("\nChoose your action:");
            TimeMethods.veryShortPause();
            System.out.println("1. Attack");
            TimeMethods.veryShortPause();
            System.out.println("2. Defend");
            TimeMethods.veryShortPause();
            System.out.println("3. Pray");
            TimeMethods.veryShortPause();
            System.out.println("4. Skip turn");
            TimeMethods.veryShortPause();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Attack Selected.\n=============");
                    if (!enemy.isPendingAttack()) {
                        charMain.attack(enemy);
                        enemy.defend(charMain.getLastAttackResult());
                    } else {
                        TimeMethods.veryShortPause();
                        System.out.println("Since the enemy attacked last turn and you didn't try do defend, you take "
                                + enemy.getLastAttackResult().getDamage() + " damage." );
                        charMain.setHealth(charMain.getHealth() - enemy.getLastAttackResult().getDamage());
                        charMain.attack(enemy);
                        enemy.defend(charMain.getLastAttackResult());
                    }
                    if (enemy.getHealth() > 0 && charMain.getHealth() > 0) {
                        enemy.attack(charMain);
                    }
                    break;
                case 2:
                    System.out.println("Defend Selected.\n=============");
                    if (!enemy.isPendingAttack()) {
                        System.out.println("No attack to defend against. You lose your turn.");
                        TimeMethods.veryShortPause();
                        enemy.attack(charMain);
                        break;
                    } else {
                        charMain.defend(enemy.getLastAttackResult());
                        break;
                    }
                case 3:
                    int result = RollSystem.rollDie(6);
                    System.out.println("Pray Selected.\n=============");
                    if (result == 6) {
                        TimeMethods.veryShortPause();
                        System.out.println("God blessed you and you healed 50 health points! Good luck! (" + result + ")");
                        TimeMethods.shortPause();
                        charMain.setHealth(charMain.getHealth() + 50);
                        enemy.setPendingAttack(false);
                    } else {
                        System.out.println("Your prayer wasn't answered. Maybe god isn't real... (" + result + ")");
                        TimeMethods.shortPause();
                        System.out.println("You take " + enemy.getLastAttackResult().getDamage() + " damage.");
                        TimeMethods.shortPause();
                        charMain.setHealth(charMain.getHealth() - enemy.getLastAttackResult().getDamage());
                        if (charMain.getHealth() <= 0) {
                            break;
                        } else {
                            enemy.attack(charMain);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Skip Selected.\n=============");
                    TimeMethods.veryShortPause();
                    System.out.println("You chose to skip your turn. Hope you have a good reason for it!");
                    TimeMethods.shortPause();
                    if (!enemy.isPendingAttack()) {
                        enemy.attack(charMain);
                    } else {
                        System.out.println("As you skip, you take " + enemy.getLastAttackResult().getDamage() + " damage.");
                        TimeMethods.veryShortPause();
                        charMain.setHealth(charMain.getHealth() - enemy.getLastAttackResult().getDamage());
                        enemy.attack(charMain);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }  while (charMain.getHealth() > 0 && enemy.getHealth() > 0);

        if (charMain.getHealth() <= 0) {
            TimeMethods.veryShortPause();
            System.out.println(charMain.getName() + " has been defeated! Game Over.");
            TimeMethods.veryShortPause();
            System.out.println("The game ends.");
            System.exit(0);
        }
        if (enemy.getHealth() <= 0) {
            TimeMethods.veryShortPause();
            System.out.println(enemy.getName() + " has been defeated! You win!");
            TimeMethods.veryShortPause();
            restartOrEndGame(charMain);
        }
    }

    public static void chooseName() {
        System.out.println("Which name do you choose?");
        String ans = scanner.nextLine();
        if (ans.isEmpty() || ans.matches(".*\\d+.*")) {
            System.out.println("Invalid name. Please choose again.");
            chooseName();
        }
        MenuSystem.name = ans;
    }

    public static Character chooseRace() {
        System.out.println("Which race do you choose? (Warrior/Mage/Elf)");
        String ans = scanner.nextLine();
        String race = ans.toUpperCase();
        if (!race.matches("WARRIOR|MAGE|ELF")) {
            System.out.println("Invalid race choice. Please choose again.");
            return chooseRace();
        }
        return switch (race) {
            case "WARRIOR" -> new Warrior(MenuSystem.name);
            case "MAGE" -> new Mage(MenuSystem.name);
            case "ELF" -> new Elf(MenuSystem.name);
            default -> null;
        };
    }

    public static Character displayMenu() {
        System.out.println("========================");
        System.out.println("Welcome to the game! =)");
        System.out.println("To start the game, press 1");
        System.out.println("To exit the game, press 2");
        System.out.println("========================");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                chooseName();
                return chooseRace();
            case "2":
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please, try again!");
                displayMenu();
        }
        return null;
    }

    public static Character createRandomEnemy() {
        return new Enemy();
    }

    public static void displayCharStats(Character character) {
        System.out.println("\n========CHAR=========");
        TimeMethods.shortPause();
        System.out.println("Name: " + character.getName());
        TimeMethods.shortPause();
        System.out.println("Race: " + character.getRace());
        TimeMethods.shortPause();
        System.out.println("Weapon: " + character.getWeapon());
        TimeMethods.shortPause();
        System.out.println("Health: " + character.getHealth());
        TimeMethods.shortPause();
        System.out.println("Attack Power: " + character.getAttackPower());
        TimeMethods.shortPause();
        System.out.println("=====================");
    }

    public static void gameMessage() {
        System.out.println("Let the game begin!");
    }

    public static String randomSituation() {
        List<String> situations = Arrays.asList("In the middle of the forest, you encounter a mysterious creature.",
                "In the middle of the night, you hear eerie sounds coming from the shadows.",
                "While exploring an ancient ruin, you stumble upon a hidden trap.",
                "As you cross a rickety bridge, it suddenly starts to collapse beneath you.",
                "You find yourself in a dark cave, and you can hear the sound of dripping water echoing around you.");
        int index = (int) (Math.random() * situations.size());
        return situations.get(index);
    }

    public static void restartOrEndGame(Character player) {
        if (player.getHealth() > 0) {
            TimeMethods.veryShortPause();
            System.out.println("Would you like to continue the journey? [Y/N]");
            String ans = scanner.next().toUpperCase();
            if (ans.matches("Y|N")) {
                if (ans.equals("Y")) {
                    TimeMethods.veryShortPause();
                    System.out.println("The journey continues...");
                    TimeMethods.veryShortPause();
                    MenuSystem.count++;
                    TimeMethods.veryShortPause();
                    System.out.println(MenuSystem.count + "x streak! Nice!");
                    TimeMethods.shortPause();
                    System.out.println(" ");
                    Character enemy1 = new Enemy();
                    player.setHealth(player.getMaxLife());
                    BattleSystem.startGame(player, enemy1);
                } else {
                    System.out.println("Game ends!");
                    System.exit(0);
                }
            } else {
                System.out.println("Invalid choice. Only Y or N is accepted");
                restartOrEndGame(player);
            }
        } else {
            System.out.println("Game over! You have been defeated.");
            System.exit(0);
        }
    }
}
