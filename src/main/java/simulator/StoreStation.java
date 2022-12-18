package simulator;

public class StoreStation extends LoadStation{
    
    private double v;
    public double getV() {
        return v;
    }

    public void setV(double v) {
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
        this.v = 0;
        this.q = null;
    }
}
