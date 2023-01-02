package simulator;

public class StoreStation extends LoadStation{
    
    private Double v;
    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    private String q;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public StoreStation(String tag) {
        super(tag);
        this.v = null;
        this.q = null;
    }
}
