package model;

public final class Ball {
    public enum Type {
        VALID, NO_BALL, WIDE;

        public static Type get(String str) {
            char c = str.charAt(0);
            switch (c) {
                case 'N':
                    return NO_BALL;
                case 'W':
                    return WIDE;
                case 'V':
                default:
                    return VALID;

            }
        }
    }

    private final Type type;
    private final Runs runs;
    private final Wicket wicket;

    // RUN(RUNTYPE)
    // BALLTYPE(B)
    // WICKETTYPE(W)
    // RUN(RUNTYPE)_BALLTYPE(B)_WICKETTYPE(W)
    public Ball(String value) {
        Type mType = Type.VALID;
        Wicket mWicket = null;
        Runs mRuns = Runs.NO_RUNS;
        String[] values = value.split("_", 3);
        for (int i = 0; i < values.length; i++) {
            String v = values[i];
            if (v.endsWith("W")) {
                mWicket = new Wicket(value);
            } else if (value.endsWith("B")) {
                mType = Type.get(value);
            } else {
                mRuns = new Runs(v);
            }
        }
        this.type = mType;
        this.runs = mRuns;
        this.wicket = mWicket;
    }

    public Ball(Type type, Runs runs, Wicket wicket) {
        this.type = type;
        this.runs = runs;
        this.wicket = wicket;
    }

    public Type getType() {
        return type;
    }

    public Runs getScoredRuns() {
        return runs;
    }

    public int getExtraRuns() {
        return type != Type.VALID ? 1 : 0;
    }

    public int getTotalRuns() {
        return runs.get() + getExtraRuns();
    }

    public boolean isDot() {
        return type == Type.VALID && runs.get() == 0;
    }

    public Wicket getWicket() {
        return wicket;
    }
}
