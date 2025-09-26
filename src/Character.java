public abstract class Character {
    private String name;
    private int health;
    private int attackPower;
    private String weapon;
    private String race;
    private AttackResult lastAttackResult;
    private int maxLife;
    private boolean pendingAttack;

    public Character(String name) {
        this.name = name;
    }

    public abstract AttackResult attack(Character opponent);

    public abstract void defend(AttackResult result);

    public abstract String chooseWeapon();

    public abstract void displayStatus();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return this.weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int AttackPower) {
        this.attackPower = AttackPower;
    }

    public String getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public AttackResult getLastAttackResult() {
        return lastAttackResult;
    }

    public void setLastAttackResult(AttackResult result) {
        this.lastAttackResult = result;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public void setPendingAttack(boolean pendingAttack) {
        this.pendingAttack = pendingAttack;
    }

    public boolean isPendingAttack() {
        return pendingAttack;
    }
}

