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

    //Ska finnas i game
//    public void doMagicMethod(magicCard m){
//        switch (m.getMagicMethod.name()){
//            case HEALPLAYER:
//
//                break;
//            case HEALCARD:
//
//                break;
//            case HEALALLCARDS:
//
//                break;
//            case DAMAGEPLAYER:
//
//                break;
//            case DAMAGECARD:
//
//                break;
//            case DAMAGEALLCARDS:
//                break;
//        }
//    }
//}
//class Main3{
//    public static void main(String[] args) {
//        MagicMethod m = MagicMethod.DAMAGEALLCARDS;
//        m.doMagicMethod(); //skicka in typen och kort och andra saker
//
//    }
}
