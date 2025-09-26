public class Enemy extends Character {
    public Enemy() {
        super(null);
        this.setRace(chooseRaceRandom());
        this.setName("Enemy");
        this.setWeapon(chooseWeapon());
    }

    public String chooseRaceRandom() {
    final String[] races = {"Warrior", "Mage", "Elf"};
    int rIndex = (int) (Math.random() * races.length);
    String choseRace = races[rIndex];
    switch (choseRace) {
        case "Warrior":
            this.setAttackPower((int) (Math.random() * 55 + 50));
            this.setHealth((int) (Math.random() * 51 + 100));
            break;
        case "Mage":
            this.setAttackPower((int) (Math.random() * 60 + 100));
            this.setHealth((int) (Math.random() * 51 + 60));
            break;
        case "Elf":
            this.setAttackPower((int) (Math.random() * 71 + 60));
            this.setHealth((int) (Math.random() * 31 + 100));
            break;
        default:
            break;
    }
        return choseRace;
    }

    @Override
    public String chooseWeapon() {
        String choseWeapon = null;
        switch (this.getRace()) {
            case "Warrior":
                String[] warriorWeapons = {"Sword", "Cutlass", "Longsword"};
                int wIndex = (int) (Math.random() * warriorWeapons.length);
                choseWeapon = warriorWeapons[wIndex];
                break;
            case "Mage":
                String[] mageWeapons = {"Staff", "Wand", "Orb"};
                int mIndex = (int) (Math.random() * mageWeapons.length);
                choseWeapon = mageWeapons[mIndex];
                break;
            case "Elf":
                String[] elfWeapons = {"Bow", "Dagger"};
                int eIndex = (int) (Math.random() * elfWeapons.length);
                choseWeapon = elfWeapons[eIndex];
                break;
        }
        return choseWeapon;
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
        System.out.println("Name: " + getName() + ", Health:  " + getHealth() + "Attacking Power: " +
                getAttackPower() + "Weapon: " + getWeapon()); ;
    }

}