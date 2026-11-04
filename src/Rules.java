public class Rules {
    public static final Rule CLASSIC = (alive, n) -> {
        if(alive){
            return n == 2 || n == 3;
        }
        return n == 3;
    };

    public static final Rule HIGHLIFE = (alive, n) -> {
        if(alive){
            return n == 2 || n == 3;
        }
        return n == 3 || n == 6;
    };

}
