public class RollSystem {

    public static int rollDie(int sides) {
        return (int) (Math.random() * sides) + 1;
    }

    public static AttackResult rollAttackPower(Character ataChar, Character defChar) {
        int basePower = ataChar.getAttackPower();
        int obtainedAttackPower = (int) (Math.random() * basePower) + 1;
        TimeMethods.mediumPause();
        System.out.println(ataChar.getName() + " rolls an attack damage of " + obtainedAttackPower +
                " and attacks " + defChar.getName() + " with " + ataChar.getWeapon().toLowerCase() + "!" +
                ((obtainedAttackPower >= 100) ? " WOW!" : " "));
        ataChar.setPendingAttack(true);
        return new AttackResult(ataChar, defChar, obtainedAttackPower, ataChar.getWeapon());
    }

    public static void rollDefensePower(AttackResult result) {
        Character ataChar = result.getCharAtt();
        Character defChar = result.getCharDef();
        int defenseTry = rollDie(6);
        switch (defenseTry) {
            case 1:
                TimeMethods.mediumPause();
                System.out.println(defChar.getName() + " rolls a dice of > 1 < and fails to defend.");
                defChar.setHealth(defChar.getHealth() - result.getDamage());
                TimeMethods.mediumPause();
                System.out.println(defChar.getName() + " receives " + result.getDamage() +
                        " damage. Remaining health: " + defChar.getHealth());
                ataChar.setPendingAttack(false);
                break;
            case 2: case 3: case 4: case 5:
                TimeMethods.mediumPause();
                int defensePercent = (defenseTry * 10) * 2 - 20;
                System.out.println(defChar.getName() + " rolls a dice of > " + defenseTry +
                        " < and partially defends " + defensePercent + "%");
                int damageReceived = result.getDamage() - (result.getDamage() * defensePercent / 100);
                defChar.setHealth(defChar.getHealth() - damageReceived);
                TimeMethods.mediumPause();
                System.out.println(defChar.getName() + " receives " + damageReceived +
                        " damage. Remaining health: " + defChar.getHealth());
                ataChar.setPendingAttack(false);
                break;
            case 6:
                TimeMethods.mediumPause();
                System.out.println(defChar.getName() + " rolls a dice of > 6 < and successfully defends.");
                TimeMethods.mediumPause();
                System.out.println(defChar.getName() + " receives 0 damage. Remaining health: " + defChar.getHealth());
                TimeMethods.mediumPause();
                System.out.println(defChar.getName() + " counterattacks " + ataChar.getName() + "! ");
                ataChar.setPendingAttack(false);
                AttackResult newAttack = RollSystem.rollAttackPower(defChar, ataChar);
                ataChar.setHealth(ataChar.getHealth() - newAttack.getDamage());
                TimeMethods.mediumPause();;
                System.out.println(ataChar.getName() + " receives " + newAttack.getDamage() +
                        " damage. Remaining health: " + ataChar.getHealth());
                defChar.setPendingAttack(false);
                break;
        }

    }
}

