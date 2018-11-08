package app;

public enum MagicMethod {
    HEALPLAYER (1),
    HEALCARD (2),
    HEALALLCARDS (3),
    DAMAGEPLAYER (4),
    DAMAGECARD (5),
    DAMAGEALLCARDS (6);

    private int methodNumber;

    private MagicMethod(int methodNumber){
        this.methodNumber = methodNumber;
    }

    private int getMethodNumber() {
        return methodNumber;
    }

    public void doMagicMethod(){
        switch (getMethodNumber()){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
    }
}
