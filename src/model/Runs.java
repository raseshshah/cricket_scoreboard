package model;

public final class Runs {

    public static Runs NO_RUNS = new Runs(Type.RUNNED, 0);

    public enum Type {
        RUNNED, FOUR, SIX, BYE;

        public static Type get(String str) {
            char c = str.charAt(str.length()-1);
            switch (c) {
                case 'B':
                    return BYE;
                case 'S':
                    return SIX;
                case 'F':
                    return FOUR;
                case 'R':
                default:
                    return RUNNED;

            }
        }
    }

    public Runs(String value) {
        if (!Character.isAlphabetic(value.charAt(value.length() - 1))) {
            this.runs = Integer.parseInt(value);
            this.type = Type.RUNNED;
        } else {
            this.runs = Integer.parseInt(value.substring(0, value.length() - 1));
            this.type = Type.get(value);
        }
    }

    public Runs(Type type, int runs) {
        this.type = type;
        this.runs = runs;
    }

    private final Type type;
    private final int runs;

    public Type type() {
        return type;
    }

    public int get() {
        return runs;
    }
}
