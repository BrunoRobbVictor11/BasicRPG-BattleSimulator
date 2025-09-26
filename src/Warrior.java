    import java.util.Scanner;

    public class Warrior extends Character implements applyAtributes {
        Scanner scanner = new Scanner(System.in);
        public Warrior(String name) {
            super(name);
            this.applyAtributes();
        }

        @Override
        public String chooseWeapon () {
            String answer = null;
            String weapon = null;
            System.out.println("Which weapon do you choose? (Sword/Cutlass/Longsword)");
            answer = scanner.nextLine();
            weapon = answer.toUpperCase();
            if (!weapon.matches("SWORD|CUTLASS|LONGSWORD")) {
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
            this.setRace("Warrior");
            this.setAttackPower((int) (Math.random() * 50 + 50));
            this.setHealth((int) (Math.random() * 51 + 100));
            this.setMaxLife(this.getHealth());
        }
    }
