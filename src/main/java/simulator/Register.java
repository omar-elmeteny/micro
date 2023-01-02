package simulator;

public class Register {
    
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

    public Register() {
        super();
        this.qi = "0";
        this.value = null;
    }
}
