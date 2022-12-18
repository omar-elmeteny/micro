package simulator;

public class Register {
    
    private String qi;
    public String getQi() {
        return qi;
    }

    public void setQi(String qi) {
        this.qi = qi;
    }

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Register() {
        super();
        this.qi = "0";
        this.value = 0;
    }
}
