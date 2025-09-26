import java.util.Scanner;

public class Mage extends Character implements applyAtributes{
    Scanner scanner = new Scanner(System.in);
    public Mage(String name) {
        super(name);
        this.applyAtributes();
    }

    @Override
    public String chooseWeapon () {
        String answer = null;
        String weapon = null;
        System.out.println("Which weapon do you choose? (Staff/Wand/Orb)");
        answer = scanner.nextLine();
        weapon = answer.toUpperCase();
        if (!weapon.matches("STAFF|WAND|ORB")) {
            System.out.println("Invalid weapon choice. Please choose again.");
            return chooseWeapon();
        }
        this.setWeapon(weapon);
        return "Weapon chose: " + this.getWeapon();
    }

    @Override
    public AttackResult attack(Character opponent) {
        AttackResult res =  RollSystem.rollAttackPower(this, opponent);
        this.setLastAttackResult(res);
        return res;
    }

    @Override
    public void defend(AttackResult result) {
        RollSystem.rollDefensePower(result);
    }

    @Override
    public void displayStatus() {
        System.out.println("Name: " + this.getName() + ", Health:  " + this.getHealth() + "Attacking Power: " +
                this.getAttackPower() + "Weapon: " + this.getWeapon()); ;
    }

    @Override
    public void applyAtributes() {
        this.setRace("Mage");
        this.setAttackPower((int) (Math.random() * 60 + 100));
        this.setHealth((int) (Math.random() * 51 + 60));
        this.setMaxLife(this.getHealth());
    }
}

