package app;

public enum AttackType {
    FIRE (0.5, 1, 2),
    WIND (2, 0.5, 1),
    WATER(1, 2, 0.5);

    private final double attackWater;
    private final double attackFire;
    private final double attackWind;

    AttackType(double attackWater, double attackFire, double attackWind) {
        this.attackWater = attackWater;
        this.attackFire = attackFire;
        this.attackWind = attackWind;
    }
    public double getAttackWaterParam(){
        return attackWater;
    }
    public double getAttackFireParam(){
        return attackFire;
    }
    public double getAttackWindParam(){
        return attackWind;
    }
}
