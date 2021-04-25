package model;

public class Wicket {
    // TODO: (good to add into construction)
    private static final boolean isStrikerOut = true;

    enum Type {
        HIT, RUN_OUT, BOWLED, STUMP, CAUGHT, OTHER;

        public static Type get(String str) {
            char c = str.charAt(0);
            switch (c) {
                case 'R':
                    return RUN_OUT;
                case 'H':
                    return HIT;
                case 'B':
                    return BOWLED;
                case 'S':
                    return STUMP;
                case 'C':
                    return CAUGHT;
                default:
                    return OTHER;
            }
        }
    }

    private final Type type;

    public Wicket(String str) {
        this.type = Type.get(str);
    }

    public Wicket(Type type) {
        this.type = type;
    }

    public boolean isStrikerOut() {
        return isStrikerOut;
    }

    public Type getType() {
        return type;
    }
}
