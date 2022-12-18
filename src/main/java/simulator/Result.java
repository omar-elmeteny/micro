package simulator;

public class Result {

    private final double value;

    public double getValue() {
        return value;
    }

    private final String tag;

    public String getTag() {
        return tag;
    }

    public Result(double value, String tag) {
        super();
        this.value = value;
        this.tag = tag;
    }
}
