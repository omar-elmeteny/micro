package simulator;

public class Register {
    
    private int index;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private String qi;
    public String getQi() {
        return qi;
    }

    public void setQi(String qi) {
        this.qi = qi;
    }

    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getValueDisplay() {
        return Double.toString(Math.round(value * 100) / 100.0);
    }

    public Register() {
        super();
        this.qi = "0";
        this.value = null;
    }
}
