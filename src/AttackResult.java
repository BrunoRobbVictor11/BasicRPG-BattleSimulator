public class AttackResult {
    private final Character charAtt;
    private final Character charDef;
    private final int damage;
    private final String weapon;

    public AttackResult(Character charAtt, Character charDef, int damage, String weapon) {
        this.charAtt = charAtt;
        this.charDef = charDef;
        this.damage = damage;
        this.weapon = weapon;
    }

    public Character getCharAtt() {return this.charAtt;}
    public Character getCharDef() {return this.charDef;}
    public int getDamage() { return this.damage; }
    public String getWeapon() { return this.weapon; }
}
