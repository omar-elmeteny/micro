package simulator;

public class Result implements Comparable<Result> {

    private final Double value;

    public Double getValue() {
        return value;
    }

    private final String tag;

    public String getTag() {
        return tag;
    }

    private final Integer issueCycle;

    public Integer getIssueCycle() {
        return issueCycle;
    }

    public Result(Double value, String tag, Integer issueCycle) {
        super();
        this.value = value;
        this.tag = tag;
        this.issueCycle = issueCycle;
    }

    public int compareTo(Result other) {
        return this.issueCycle - other.issueCycle;
    }
}
