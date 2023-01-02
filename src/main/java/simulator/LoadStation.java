package simulator;

public class LoadStation {

    private final String tag;

    public String getTag() {
        return tag;
    }

    private boolean busy;

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    private Integer address;

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    private Integer issueCycle;
    
    public Integer getIssueCycle() {
        return issueCycle;
    }

    public void setIssueCycle(Integer issueCycle) {
        this.issueCycle = issueCycle;
    }

    private Integer numberOfCyclesLeft;

    public Integer getNumberOfCyclesLeft() {
        return numberOfCyclesLeft;
    }

    public void setNumberOfCyclesLeft(Integer numberOfCyclesLeft) {
        this.numberOfCyclesLeft = numberOfCyclesLeft;
    }

    private Double value;
    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LoadStation(String tag) {
        super();
        this.tag = tag;
        this.busy = false;
        this.address = null;
        this.issueCycle = null;
        this.numberOfCyclesLeft = null;
        this.value = null;
    }

}
